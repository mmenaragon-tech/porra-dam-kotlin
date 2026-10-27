package org.elCampico.PorraDam.screens.main.jornada

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import org.elCampico.PorraDam.screens.main.data.Partido
import org.elCampico.PorraDam.screens.main.data.PronosticoJornada
import org.elCampico.PorraDam.screens.main.data.PronosticoPartido
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.elCampico.PorraDam.screens.main.data.Equipo
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
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FondoPantalla()
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

