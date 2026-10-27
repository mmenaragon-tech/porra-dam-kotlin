package org.elCampico.PorraDam.screens.main.data

import kotlinx.serialization.Serializable

@Serializable
data class Partido(
    private val idPartido : Int,
    private val equipoLocal : Equipo,
    private val equipoVisitante : Equipo,
    private var marcador : Marcador
) {
    fun getLocal() : Equipo {
        return this.equipoLocal;
    }
    fun getVisitante() : Equipo {
        return this.equipoVisitante;
    }
    fun getMarcador() {
        marcador.getLocal()
        marcador.getVisitante()
    }
}