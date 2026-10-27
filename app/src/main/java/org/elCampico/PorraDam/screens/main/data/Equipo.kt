package org.elCampico.PorraDam.screens.main.data

import kotlinx.serialization.Serializable

@Serializable
data class Equipo(
    private val escudo: String,
    private val nombre: String,
    private val abreviatura: String
) {
    fun getEscudo() : String {
        return this.escudo;
    }
    fun getNombre() : String {
        return this.nombre;
    }
    fun getAbvt() : String {
        return this.abreviatura;
    }
}