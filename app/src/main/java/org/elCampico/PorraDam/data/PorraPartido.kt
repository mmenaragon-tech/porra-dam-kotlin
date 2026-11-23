package org.elCampico.PorraDam.data

import kotlin.collections.get

data class PorraPartido(
    val resultado: ResultadoPartido,
    val porra: Porra
)

val porrasPartido = listOf(
    PorraPartido(resultados[0], porras[0]),
    PorraPartido(resultados[1], porras[1]),
    PorraPartido(resultados[2], porras[2]),
    PorraPartido(resultados[3], porras[3]),
    PorraPartido(resultados[4], porras[4])
)