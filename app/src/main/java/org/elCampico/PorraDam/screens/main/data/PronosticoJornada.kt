package org.elCampico.PorraDam.screens.main.data

import kotlinx.serialization.Serializable

@Serializable
data class PronosticoJornada(
    private val jornada : String,
    private val partidos : ArrayList<PronosticoPartido>
) {
}