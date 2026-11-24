package org.elCampico.PorraDam.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import org.elCampico.PorraDam.data.model.ColoresAvatar
import org.elCampico.PorraDam.data.model.Equipo
import org.elCampico.PorraDam.data.model.Jornada
import org.elCampico.PorraDam.data.model.Partido
import org.elCampico.PorraDam.data.model.Porra
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.ResultadoPartido
import org.elCampico.PorraDam.data.model.Sala
import org.elCampico.PorraDam.data.model.Usuario
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

private fun leerArray(contexto: Context, archivo: String): JSONArray {
    val propio = File(contexto.filesDir, archivo)
    val texto = if (propio.exists()) propio.readText()
    else contexto.assets.open(archivo).bufferedReader().use { it.readText() }
    return JSONArray(texto)
}

fun leerUsuarios(contexto: Context, colores: Map<Int, ColoresAvatar>): List<Usuario> {
    val array = leerArray(contexto, "usuarios.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        Usuario(
            id = objeto.getInt("id"),
            nombre = objeto.getString("nombre"),
            posicion = objeto.getInt("posicion"),
            puntos = objeto.getInt("puntos"),
            foto = if (objeto.isNull("foto")) null else objeto.getInt("foto"),
            avatar = colores.getValue(objeto.getInt("avatar"))
        )
    }
}

fun leerColoresAvatar(contexto: Context): Map<Int, ColoresAvatar> {
    val texto = contexto.assets.open("coloresAvatar.json")
        .bufferedReader()
        .use { it.readText() }

    val objeto = JSONObject(texto)

    return objeto.keys().asSequence().associate { clave ->
        val colores = objeto.getJSONObject(clave)
        clave.toInt() to ColoresAvatar(
            fondo = Color(android.graphics.Color.parseColor(colores.getString("fondo"))),
            letra = Color(android.graphics.Color.parseColor(colores.getString("letra")))
        )
    }
}

fun leerEquipos(contexto: Context): List<Equipo> {
    val array = leerArray(contexto, "equipos.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        Equipo(
            nombre = objeto.getString("nombre"),
            escudo = contexto.resources.getIdentifier(
                objeto.getString("escudo"), "drawable", contexto.packageName
            )
        )
    }
}

fun leerPartidos(contexto: Context, equipos: List<Equipo>): List<Partido> {
    val array = leerArray(contexto, "partidos.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        Partido(
            id = objeto.getInt("id"),
            local = equipos.first { it.nombre == objeto.getString("local") },
            visitante = equipos.first { it.nombre == objeto.getString("visitante") }
        )
    }
}

fun leerJornadas(contexto: Context, partidos: List<Partido>): List<Jornada> {
    val array = leerArray(contexto, "partidos.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        objeto.getInt("jornada") to objeto.getInt("id")
    }
        .groupBy({ it.first }, { (_, id) -> partidos.first { it.id == id } })
        .map { (numero, lista) -> Jornada(numero, lista) }
        .sortedBy { it.numero }
}

fun leerPorras(contexto: Context): List<Porra> {
    val array = leerArray(contexto, "porras.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        Porra(
            usuarioId = objeto.getInt("usuario"),
            partidoId = objeto.getInt("partido"),
            golesLocal = objeto.getInt("golesLocal"),
            golesVisitante = objeto.getInt("golesVisitante")
        )
    }
}

fun leerResultados(contexto: Context, partidos: List<Partido>): List<ResultadoPartido> {
    val array = leerArray(contexto, "resultados.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        ResultadoPartido(
            partido = partidos.first { it.id == objeto.getInt("partido") },
            golesLocal = objeto.getInt("golesLocal"),
            golesVisitante = objeto.getInt("golesVisitante")
        )
    }
}

fun leerPorrasPartido(
    resultados: List<ResultadoPartido>,
    porras: List<Porra>,
    usuarioId: Int
): List<PorraPartido> =
    resultados.mapNotNull { resultado ->
        porras.firstOrNull {
            it.usuarioId == usuarioId && it.partidoId == resultado.partido.id
        }?.let { PorraPartido(resultado, it) }
    }

fun leerSalas(contexto: Context, usuarios: List<Usuario>): List<Sala> {
    val array = leerArray(contexto, "salas.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        val ids = objeto.getJSONArray("usuarios")
        Sala(
            nombre = objeto.getString("nombre"),
            usuarios = List(ids.length()) { j ->
                val id = ids.getInt(j)
                usuarios.first { it.id == id }
            }
        )
    }
}

fun guardarPorras(contexto: Context, nuevas: List<Porra>) {
    val claves = nuevas.map { it.usuarioId to it.partidoId }.toSet()
    val todas = leerPorras(contexto)
        .filterNot { (it.usuarioId to it.partidoId) in claves } + nuevas

    val array = JSONArray()
    todas.forEach { porra ->
        array.put(JSONObject().apply {
            put("usuario", porra.usuarioId)
            put("partido", porra.partidoId)
            put("golesLocal", porra.golesLocal)
            put("golesVisitante", porra.golesVisitante)
        })
    }
    File(contexto.filesDir, "porras.json").writeText(array.toString(2))
}