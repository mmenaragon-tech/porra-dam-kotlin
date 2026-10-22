package org.elCampico.PorraDam.ui.main.data

import kotlinx.serialization.Serializable

@Serializable
data class PronosticoJornada(
    private val jornada : String,
    private val partidos : ArrayList<PronosticoPartido>
) {
}