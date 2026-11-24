package org.elCampico.PorraDam.data.model

import androidx.annotation.DrawableRes

data class Usuario(
    val id: Int,
    val nombre: String,
    val posicion: Int,
    val puntos: Int,
    @DrawableRes val foto: Int? = null,
    val avatar: ColoresAvatar
){
    companion object {
        const val USUARIO_ACTUAL = 4
    }
}



