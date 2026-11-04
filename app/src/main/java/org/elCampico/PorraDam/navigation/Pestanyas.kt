package org.elCampico.PorraDam.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.graphics.vector.ImageVector

enum class Pestanyas (val titulo: String, val icono: ImageVector) {
    JORNADA("Jornada", Icons.Default.DateRange),
    RESULTADOS("Resultados", Icons.Default.CheckCircle)
}