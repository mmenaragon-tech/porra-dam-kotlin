package org.elCampico.PorraDam.data


data class ResultadoPartido(
    val partido: Partido,
    val golesLocal: Int,
    val golesVisitante: Int
)

val resultados = listOf(
    ResultadoPartido(partidos[0], 2, 1),
    ResultadoPartido(partidos[1], 3, 2),
    ResultadoPartido(partidos[2], 3, 2),
    ResultadoPartido(partidos[3], 1, 1),
    ResultadoPartido(partidos[4], 1, 0),
)