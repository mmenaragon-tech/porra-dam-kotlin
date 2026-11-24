package org.elCampico.PorraDam.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import org.elCampico.PorraDam.data.model.ColoresAvatar
import org.elCampico.PorraDam.data.model.Equipo
import org.elCampico.PorraDam.data.model.Partido
import org.elCampico.PorraDam.data.model.Porra
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.ResultadoPartido
import org.elCampico.PorraDam.data.model.Sala
import org.elCampico.PorraDam.data.model.Usuario
import org.json.JSONArray
import org.json.JSONObject

private fun leerArray(contexto: Context, archivo: String): JSONArray {
    val texto = contexto.assets.open(archivo)
        .bufferedReader()
        .use { it.readText() }
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
            local = equipos.first { it.nombre == objeto.getString("local") },
            visitante = equipos.first { it.nombre == objeto.getString("visitante") }
        )
    }
}

fun leerPorras(contexto: Context, partidos: List<Partido>): List<Porra> {
    val array = leerArray(contexto, "porras.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        Porra(
            partido = partidos[objeto.getInt("partido")],
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
            partido = partidos[objeto.getInt("partido")],
            golesLocal = objeto.getInt("golesLocal"),
            golesVisitante = objeto.getInt("golesVisitante")
        )
    }
}

fun leerPorrasPartido(
    contexto: Context,
    resultados: List<ResultadoPartido>,
    porras: List<Porra>
): List<PorraPartido> {
    val array = leerArray(contexto, "porrasPartido.json")

    return List(array.length()) { i ->
        val objeto = array.getJSONObject(i)
        PorraPartido(
            resultado = resultados[objeto.getInt("resultado")],
            porra = porras[objeto.getInt("porra")]
        )
    }
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