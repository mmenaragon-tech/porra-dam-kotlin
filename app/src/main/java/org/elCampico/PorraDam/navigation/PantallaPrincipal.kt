package org.elCampico.PorraDam.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.elCampico.PorraDam.screens.main.jornada.PantallaJornada
import org.elCampico.PorraDam.screens.main.resultados.PantallaResultados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    alias: String,
    modifier: Modifier = Modifier
) {
    var pestanya by rememberSaveable { mutableStateOf(Pestanyas.JORNADA) }

    BackHandler(enabled = pestanya != Pestanyas.JORNADA) {
        pestanya = Pestanyas.JORNADA
    }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = {Text(pestanya.titulo)}) },
        bottomBar = {
            NavigationBar {
                Pestanyas.entries.forEach { p ->
                    NavigationBarItem(
                        selected = pestanya == p,
                        onClick = { pestanya = p },
                        icon = { Icon(p.icono, contentDescription = null) },
                        label = { Text(p.titulo) }
                    )
                }
            }
        }
    ) { innerPading ->
        val m = Modifier.padding(innerPading)

        when (pestanya) {
            Pestanyas.JORNADA -> PantallaJornada(m)
            Pestanyas.RESULTADOS -> PantallaResultados(m)

        }
    }
}