package org.elCampico.PorraDam.data

import androidx.annotation.DrawableRes

data class Usuario(
    val nombre: String,
    val posicion: Int,
    val puntos: Int,
    @DrawableRes val foto: Int? = null
)