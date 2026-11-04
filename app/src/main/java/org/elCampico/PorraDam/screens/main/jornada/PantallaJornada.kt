package org.elCampico.PorraDam.screens.main.jornada

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.elCampico.PorraDam.data.Partido
import org.elCampico.PorraDam.data.PronosticoPartido
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elCampico.PorraDam.data.Equipo
import org.elCampico.PorraDam.ui.theme.PorraDamTheme
import org.elCampico.PorraDam.ui.utils.FondoPantalla

@Composable
fun PantallaJornada(
    modifier : Modifier = Modifier,

    /*
    numero : Int? = 1,
    partidos : List<PronosticoPartido>,
    onGuardarPronostico : (pronostico : PronosticoJornada) -> Unit

     */

) {
    /*
    var golesLocal by rememberSaveable(numero) { mutableStateOf(List(partidos.size) { "" }) }
    var golesVisitante by rememberSaveable(numero) { mutableStateOf(List(partidos.size) { "" }) }
    */
    var golesLocal by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
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
                Icons.Default.CalendarMonth, "Jornada",
                tint = Color.White
            )
            Text(
                "Se cierra el: Sáb 12 oct - 16:00",
                color = Color.White
            )
        }

        Box(
            modifier = Modifier.fillMaxHeight().
            background(Color.White)
        ){
            Box() {
                Row() { SelectorGoles(
                    goles = golesLocal,
                    onGolesChange = { golesLocal = it }
                )}
                Row() { }
            }
            Box() {
                Row() { }
                Row() { }
            }
            Box() {
                Row() { }
                Row() { }
            }
            Box() {
                Row() { }
                Row() { }
            }
            Box() {
                Row() { }
                Row() { }
            }
            Button(onClick = {}) {

            }
        }
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
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Text("$goles", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "Restar gol",
                modifier = Modifier.size(16.dp)
            )
        }

        // Flecha hacia arriba
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(32.dp)
                .border(1.dp, Color.LightGray, forma)
                .clip(forma)
                .clickable(enabled = goles < max) { onGolesChange(goles + 1) }
        ) {
            Icon(
                Icons.Default.KeyboardArrowUp,
                contentDescription = "Sumar gol",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun PartidosJornada(jornada: String, jornadas: Map<String, ArrayList<Partido>>) {
    jornadas[jornada]?.forEach { partido ->
       /* Card() {
            Label() { }
        }*/
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaJornadaPreview() {
    val partidos = listOf(
        PronosticoPartido(
            idPartido = 1,
            equipoLocal = Equipo(nombre = "Real Madrid", escudo = "", abreviatura = "RM"),
            equipoVisitante = Equipo(nombre = "Barcelona", escudo = "", abreviatura = "FCB")
        ),
        PronosticoPartido(
            idPartido = 2,
            equipoLocal = Equipo(nombre = "Sevilla", escudo = "", abreviatura = "SFC"),
            equipoVisitante = Equipo(nombre = "Valencia", escudo = "", abreviatura = "VCF")
        ),
        PronosticoPartido(
            idPartido = 3,
            equipoLocal = Equipo(nombre = "Betis", escudo = "", abreviatura = "BET"),
            equipoVisitante = Equipo(nombre = "Villarreal", escudo = "", abreviatura = "VRF")
        )
    )

    PorraDamTheme {
        PantallaJornada(
            /*
            numero = 12,
            partidos = partidos,
            onGuardarPronostico = { }
            
             */
        )
    }
}

