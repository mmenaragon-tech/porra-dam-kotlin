
package org.elCampico.PorraDam.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import org.elCampico.PorraDam.data.model.ColoresAvatar
import org.elCampico.PorraDam.data.model.Equipo
import org.elCampico.PorraDam.data.model.Jornada
import org.elCampico.PorraDam.data.model.Partido
import org.elCampico.PorraDam.data.model.Porra
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.Sala
import org.elCampico.PorraDam.data.model.Usuario
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

private fun leerTexto(contexto: Context, archivo: String): String {
    val propio = File(contexto.filesDir, archivo)
    return if (propio.exists()) propio.readText()
    else contexto.assets.open(archivo).bufferedReader().use { it.readText() }
}

private fun leerArray(contexto: Context, archivo: String): JSONArray =
    JSONArray(leerTexto(contexto, archivo))

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
                objeto.getString("escudo"),
                "drawable",
                contexto.packageName
            )
        )
    }
}

fun leerJornadas(contexto: Context, equipos: List<Equipo>): List<Jornada> {
    val array = JSONObject(leerTexto(contexto, "jornadas.json")).getJSONArray("jornadas")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        val partidos = objeto.getJSONArray("partidos")

        Jornada(
            numero = objeto.getInt("numero"),
            partidos = List(partidos.length()) { j ->
                val p = partidos.getJSONObject(j)

                Partido(
                    id = p.getInt("id"),
                    local = equipos.first { it.nombre == p.getString("local") },
                    visitante = equipos.first { it.nombre == p.getString("visitante") },
                    golesLocal = if (p.isNull("golesLocal")) null else p.getInt("golesLocal"),
                    golesVisitante = if (p.isNull("golesVisitante")) null else p.getInt("golesVisitante"),
                    fecha = p.getString("fecha"),
                    hora = p.getString("hora")
                )
            }
        )
    }.sortedBy { it.numero }
}

fun leerPartidos(jornadas: List<Jornada>): List<Partido> =
    jornadas.flatMap { it.partidos }

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

fun leerPorrasPartido(
    partidos: List<Partido>,
    porras: List<Porra>,
    usuarioId: Int
): List<PorraPartido> =
    partidos.map { partido ->
        PorraPartido(
            partido,
            porras.firstOrNull {
                it.usuarioId == usuarioId && it.partidoId == partido.id
            }
        )
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
