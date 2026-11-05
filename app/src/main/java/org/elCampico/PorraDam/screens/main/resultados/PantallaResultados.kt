package org.elCampico.PorraDam.screens.main.resultados

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elCampico.PorraDam.ui.theme.PorraDamTheme

data class ResultadoPartido(
    val local: String,
    val visitante: String,
    val golesLocal: Int,
    val golesVisitante: Int,
    val puntos: Int
)

data class PorraPartido(
    val golesLocal: Int,
    val golesVisitante: Int,
    val colorFondo: Color,
    val colorPorra: Color
)

@Composable
fun PantallaResultados(modifier: Modifier = Modifier) {
    val resultados = listOf(
        ResultadoPartido("Atlético Madrid", "Real Madrid", 2, 1, 3),
        ResultadoPartido("FC Barcelona", "Villarreal CF", 3, 0, 0),
        ResultadoPartido("Sevilla FC", "Real Sociedad", 1, 1, 5),
        ResultadoPartido("Betis ", "Valencia FC", 2, 1, 0),
        ResultadoPartido("Athletic Club", "Celta de Vigo", 1, 0, 8),
    )

    val porras = listOf(
        PorraPartido(2, 1, Color(0xFFE8F5E9), Color.Green),
        PorraPartido(2, 0, Color.LightGray, Color.Black),
        PorraPartido(1, 0, Color.LightGray, Color.Black),
        PorraPartido(1, 1, Color(0xFFFFEBEE), Color.Red),
        PorraPartido(1, 0, Color(0xFFE8F5E9), Color.Green)
    )

    val totalPuntos = resultados.sumOf { it.puntos }
    var colorPuntos = Color.Black

    if (totalPuntos > 0) {
        colorPuntos = Color.Green
    } else if (totalPuntos < 0) {
        colorPuntos = Color.Red
    }

    Column(
        modifier = modifier.fillMaxSize()
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(Color.White)
            .padding(vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Resultados", color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text("Jornada")
                Icon(Icons.Default.KeyboardArrowDown, "Flecha")
            }
        }
        Text(
            "Resultados oficiales y puntos obtenidos",
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
            color = Color.Black
        )
        /*
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            resultados.zip(porras).forEach { (resultado, pronostico) ->
                ResultadoPartido(resultado, pronostico)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFEEEEEE))
                        .padding(12.dp)

                ) {
                    Icon(
                        Icons.Default.Star, "Estrella",
                        tint = Color.Yellow,
                        modifier = Modifier.size(38.dp)
                    )
                    Text(
                        "Total jornada",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .weight(1f)
                    )
                    Text(
                        "+$totalPuntos",
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                            .padding(12.dp),
                        color = colorPuntos,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

        */
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(resultados.zip(porras)) { (resultado, pronostico) ->
                ResultadoPartido(resultado, pronostico)
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .fillMaxWidth()
                        .background(color = Color(0xFFEEEEEE))
                        .padding(12.dp)

                ) {
                    Icon(
                        Icons.Default.Star, "Estrella",
                        tint = Color.Yellow,
                        modifier = Modifier.size(38.dp)
                    )
                    Text(
                        "Total jornada",
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .weight(1f)
                    )
                    Text(
                        "+$totalPuntos",
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                            .padding(12.dp),
                        color = colorPuntos,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }

    }
}

@Composable
fun ResultadoPartido(resultado: ResultadoPartido, porra: PorraPartido) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilaResultado(resultado.local, resultado.golesLocal)
            FilaResultado(resultado.visitante, resultado.golesVisitante)
            Text(
                "Tu porra: ${porra.golesLocal} - ${porra.golesVisitante}",
                color = porra.colorPorra,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(porra.colorFondo, RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp)
            )
        }
        Box(
            
            modifier = Modifier
                .align(Alignment.Top)
                .padding(start = 20.dp, top = 12.dp)
                .background(porra.colorFondo, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                "+${resultado.puntos}",
                color = porra.colorPorra,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun FilaResultado(nombre: String, goles: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(nombre, color = Color.Black)
        Text(
            "$goles",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaResultadosPreview() {
    PorraDamTheme {
        PantallaResultados()
    }
}