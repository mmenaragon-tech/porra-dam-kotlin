package org.elCampico.PorraDam.data.model


data class Partido(
    val id: Int,
    val local: Equipo,
    val visitante: Equipo,
    val golesLocal: Int? = null,
    val golesVisitante: Int? = null,
    val fecha: String,
    val hora: String
) {
    val jugado: Boolean get() = golesLocal != null && golesVisitante != null
}

