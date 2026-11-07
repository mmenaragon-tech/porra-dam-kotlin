package org.elCampico.PorraDam.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.ui.graphics.vector.ImageVector

enum class Pestanyas(val titulo: String, val icono: ImageVector) {
    JORNADA("Jornada", Icons.Default.CalendarMonth),
    RESULTADOS("Resultados", Icons.Default.SportsSoccer),
    CLASIFICACION("Clasificacion", Icons.Default.Leaderboard)
}