package org.elCampico.PorraDam.screens.main.jornada

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.elCampico.PorraDam.model.guardarPorras
import org.elCampico.PorraDam.model.leerEquipos
import org.elCampico.PorraDam.model.leerPartidos
import org.elCampico.PorraDam.model.leerPorras
import org.elCampico.PorraDam.model.leerPorrasPartido
import org.elCampico.PorraDam.model.leerResultados
import org.elCampico.PorraDam.ui.theme.PorraDamTheme


@Composable
fun PantallaJornada(
    modifier: Modifier = Modifier,
    vm: JornadasViewModel = viewModel()
) {
    val contexto = LocalContext.current
    LaunchedEffect(Unit) {
        val equipos = leerEquipos(contexto)
        val partidos = leerPartidos(contexto, equipos)
        vm.cargar(partidos)
    }
    // Un valor de goles por partido y equipo
    val golesLocal = remember(vm.partidosEnviados.size) { mutableStateListOf(*Array(vm.partidosEnviados.size) { 0 }) }
    val golesVisitante = remember(vm.partidosEnviados.size) { mutableStateListOf(*Array(vm.partidosEnviados.size) { 0 }) }

    // Solo el contenedor raíz usa "modifier" (trae el padding del Scaffold)
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            "Jornada 1",
            modifier = Modifier.padding(horizontal = 18.dp),
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Tu porra para esta jornada",
            modifier = Modifier.padding(horizontal = 18.dp),
            color = Color.White
        )
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Icon(
                Icons.Default.CalendarMonth,
                contentDescription = "Jornada",
                tint = Color.White
            )
            Text(
                "Se cierra el: Sáb 12 oct - 16:00",
                color = Color.White
            )
        }

        // Panel blanco con los partidos
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.White)
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            /*
            partidos.forEachIndexed { i, (local, visitante) ->
                PartidoItem(
                    local = local,
                    visitante = visitante,
                    golesLocal = golesLocal[i],
                    golesVisitante = golesVisitante[i],
                    onGolesLocalChange = { golesLocal[i] = it },
                    onGolesVisitanteChange = { golesVisitante[i] = it }
                )
            }

            */
            itemsIndexed(vm.partidosEnviados) { i, (equipo1, equipo2) ->
                PartidoItem(
                    escudoLocal = equipo1.escudo,
                    escudoVisitante = equipo2.escudo,
                    local = equipo1.nombre,
                    visitante = equipo2.nombre,
                    golesLocal = golesLocal[i],
                    golesVisitante = golesVisitante[i],
                    onGolesLocalChange = { golesLocal[i] = it },
                    onGolesVisitanteChange = { golesVisitante[i] = it }
                )
            }
            /*
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /* guardar pronósticos */ },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        "Guardar pronósticos",
                        fontSize = 20.sp
                    )
                }
            }

            */

            item {
                Button(
                    onClick = {
                        val porras = vm.construirPorras(golesLocal, golesVisitante)
                        guardarPorras(contexto, porras, vm.partidosEnviados)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 20.dp, vertical = 15.dp)

                ) {
                    Text("Guardar pronósticos", fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun PartidoItem(
    escudoLocal: Int,
    escudoVisitante: Int,
    local: String,
    visitante: String,
    golesLocal: Int,
    golesVisitante: Int,
    onGolesLocalChange: (Int) -> Unit,
    onGolesVisitanteChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilaEquipo(escudoLocal, local, golesLocal, onGolesLocalChange)
        FilaEquipo(escudoVisitante, visitante, golesVisitante, onGolesVisitanteChange)
    }
}

@Composable
fun FilaEquipo(
    escudo: Int,
    nombre: String,
    goles: Int,
    onGolesChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = escudo),
                contentDescription = "Escudo de $nombre",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Text(nombre, color = Color.Black)
        SelectorGoles(goles = goles, onGolesChange = onGolesChange)
    }
}

@Composable
fun SelectorGoles(
    goles: Int,
    onGolesChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    max: Int = 9
) {
    val forma = RoundedCornerShape(8.dp)

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Número + flecha hacia abajo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .border(1.dp, Color.LightGray, forma)
                .clip(forma)
                .clickable(enabled = goles > 0) { onGolesChange(goles - 1) }
                .padding(horizontal = 10.dp, vertical = 2.dp)
        ) {
            Text(
                "$goles",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "Restar gol",
                tint = Color.Black,
                modifier = Modifier.size(16.dp)
            )
        }

        // Flecha hacia arriba
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(28.dp)
                .border(1.dp, Color.LightGray, forma)
                .clip(forma)
                .clickable(enabled = goles < max) { onGolesChange(goles + 1) }
        ) {
            Icon(
                Icons.Default.KeyboardArrowUp,
                contentDescription = "Sumar gol",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaJornadaPreview() {
    PorraDamTheme {
        PantallaJornada()
    }
}