package org.elCampico.PorraDam.data


data class Partido (
    val local: Equipo,
    val visitante: Equipo
)

val partidos = listOf(
    Partido(atletico, realMadrid),
    Partido(barcelona, villarreal),
    Partido(sevilla, realSociedad),
    Partido(betis, valencia),
    Partido(athletic, celta),
)