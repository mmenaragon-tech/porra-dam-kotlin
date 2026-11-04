package org.elCampico.PorraDam.screens.main.inicio

import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitOutside
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elCampico.PorraDam.screens.main.clasificacion.PantallaClasificacion
import org.elCampico.PorraDam.screens.main.jornada.PantallaJornada
import org.elCampico.PorraDam.screens.main.resultados.PantallaResultados
import org.elCampico.PorraDam.ui.theme.PorraDamTheme
import org.elCampico.PorraDam.ui.utils.FondoPantalla

enum class Destino(val titulo: String, val icono: ImageVector) {
    JORNADA("Jornada", Icons.Default.CalendarMonth),
    RESULTADOS("Resultados", Icons.Default.SportsSoccer),
    CLASIFICACION("Clasificacion", Icons.Default.Leaderboard)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio() {
    var destinoActual by rememberSaveable { mutableStateOf(Destino.JORNADA) }
    val forma = RoundedCornerShape(12.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        FondoPantalla(modifier = Modifier.fillMaxSize().blur(8.dp))
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(

                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menú",
                                modifier = Modifier.align(Alignment.CenterStart)
                            )

                            Text(
                                "Porra DAM",
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .background(Color(0xFFE8F5E9), forma)
                                    .border(3.dp, Color(0xFFE8F5E9), forma)
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text("38 pts", color = Color(0xFF2E7D32), fontSize = 17.sp)
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = Color.Transparent,
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                NavigationBar() {
                    Destino.entries.forEach { destino ->
                        NavigationBarItem(
                            selected = destinoActual == destino,
                            onClick = { destinoActual = destino },
                            icon = { Icon(destino.icono, contentDescription = destino.titulo) },
                            label = { Text(destino.titulo) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            when (destinoActual) {
                Destino.JORNADA -> PantallaJornada(modifier)
                Destino.RESULTADOS -> PantallaResultados(modifier)
                Destino.CLASIFICACION -> PantallaClasificacion(modifier)
            }
        }
    }
}

@Preview
@Composable
fun InicioPreview() {
    PorraDamTheme {
        Inicio()
    }
}