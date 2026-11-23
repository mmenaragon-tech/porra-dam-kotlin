package org.elCampico.PorraDam.data

import androidx.compose.ui.graphics.Color

data class ColoresAvatar(
    val fondo: Color,
    val letra: Color
)

val coloresAvatar: Map<Int, ColoresAvatar> = mapOf(
    0 to ColoresAvatar(Color(0xFFFFEBEE), Color(0xFFC62828)), // rojo
    1 to ColoresAvatar(Color(0xFFFFF3E0), Color(0xFFE65100)), // naranja
    2 to ColoresAvatar(Color(0xFFFFF8E1), Color(0xFFF57F17)), // ámbar
    3 to ColoresAvatar(Color(0xFFE8F5E9), Color(0xFF2E7D32)), // verde
    4 to ColoresAvatar(Color(0xFFE0F2F1), Color(0xFF00695C)), // verde azulado
    5 to ColoresAvatar(Color(0xFFE1F5FE), Color(0xFF0277BD)), // azul claro
    6 to ColoresAvatar(Color(0xFFE8EAF6), Color(0xFF283593)), // índigo
    7 to ColoresAvatar(Color(0xFFF3E5F5), Color(0xFF6A1B9A)), // morado
    8 to ColoresAvatar(Color(0xFFFCE4EC), Color(0xFFAD1457)), // rosa
    9 to ColoresAvatar(Color(0xFFEFEBE9), Color(0xFF4E342E))  // marrón
)