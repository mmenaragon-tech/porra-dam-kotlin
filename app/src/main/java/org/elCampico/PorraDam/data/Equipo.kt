package org.elCampico.PorraDam.data

import androidx.annotation.DrawableRes
import org.elCampico.PorraDam.R

data class Equipo(
    val nombre: String,
    @DrawableRes val escudo: Int
)

val atletico = Equipo("Atlético Madrid", R.drawable.atm)
val realMadrid = Equipo("Real Madrid", R.drawable.rm)
val barcelona = Equipo("FC Barcelona", R.drawable.blc)
val villarreal = Equipo("Villarreal CF", R.drawable.villa)
val sevilla = Equipo("Sevilla FC", R.drawable.sev)
val realSociedad = Equipo("Real Sociedad", R.drawable.rs)
val betis = Equipo("Betis", R.drawable.betis)
val valencia = Equipo("Valencia CF", R.drawable.vlc)
val athletic = Equipo("Athletic Club", R.drawable.ath)
val celta = Equipo("Celta de Vigo", R.drawable.celta)

