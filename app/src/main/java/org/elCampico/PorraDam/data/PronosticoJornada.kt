package org.elCampico.PorraDam.data

import kotlinx.serialization.Serializable

@Serializable
data class PronosticoJornada(
    private val jornada : String,
    private val partidos : ArrayList<PronosticoPartido>
) {
}