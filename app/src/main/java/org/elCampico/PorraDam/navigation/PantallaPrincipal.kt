package org.elCampico.PorraDam.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elCampico.PorraDam.screens.main.clasificacion.PantallaClasificacion
import org.elCampico.PorraDam.screens.main.inicio.Destino
import org.elCampico.PorraDam.screens.main.jornada.PantallaJornada
import org.elCampico.PorraDam.screens.main.resultados.PantallaResultados
import org.elCampico.PorraDam.ui.utils.FondoPantalla

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPrincipal(
    alias: String,
    modifier: Modifier = Modifier
) {
    var pestanya by rememberSaveable { mutableStateOf(Pestanyas.JORNADA) }
    val forma = RoundedCornerShape(12.dp)

    BackHandler(enabled = pestanya != Pestanyas.JORNADA) {
        pestanya = Pestanyas.JORNADA
    }

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
                    Pestanyas.entries.forEach { destino ->
                        NavigationBarItem(
                            selected = pestanya == destino,
                            onClick = { pestanya = destino },
                            icon = { Icon(destino.icono, contentDescription = destino.titulo) },
                            label = { Text(destino.titulo) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            when (pestanya) {
                Pestanyas.JORNADA -> PantallaJornada(modifier)
                Pestanyas.RESULTADOS -> PantallaResultados(modifier)
                Pestanyas.CLASIFICACION -> PantallaClasificacion(modifier)
            }
        }
    }
}