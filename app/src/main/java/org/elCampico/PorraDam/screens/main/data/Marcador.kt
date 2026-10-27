package org.elCampico.PorraDam.screens.main.data

data class Marcador(
    private var local : Int? = null,
    private var visitante : Int? = null
) {
    fun getLocal() : Int? {
        return this.local;
    }
    fun getVisitante() : Int? {
        return this.visitante;
    }
    fun setLocal(local : Int) {
        this.local = local;
    }
    fun setVisitante(visitante : Int) {
        this.visitante = visitante;
    }
}