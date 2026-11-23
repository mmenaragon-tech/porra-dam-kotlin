package org.elCampico.PorraDam.data

data class Porra (
    val partido: Partido,
    val golesLocal: Int,
    val golesVisitante: Int,
)

val porras = listOf(
    Porra(partidos[0], 2, 1),
    Porra(partidos[1], 2, 0),
    Porra(partidos[2], 1, 0),
    Porra(partidos[3], 1, 1),
    Porra(partidos[4], 1, 0)
)
