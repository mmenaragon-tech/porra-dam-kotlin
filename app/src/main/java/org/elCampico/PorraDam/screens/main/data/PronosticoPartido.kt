package org.elCampico.PorraDam.screens.main.data

data class PronosticoPartido(
    private val idPartido : Int,
    private val equipoLocal : Equipo,
    private val equipoVisitante : Equipo,
    private var marcador : Marcador? = null
) {
    fun setMarcador(local : Int, visitante : Int) {
        marcador?.setLocal(local)
        marcador?.setVisitante(visitante)
    }
    fun getMarcador() {
        marcador?.getLocal()
        marcador?.getVisitante()
    }
}