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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ResultadoPartido(
    val local: String,
    val visitante: String,
    val golesLocal: Int,
    val golesVisitante: Int,
    val puntos: Int
)

@Composable
fun PantallaResultados(modifier: Modifier = Modifier) {
    val resultados = listOf(
        ResultadoPartido("Atlético Madrid", "Real Madrid", 2, 1, 3),
        ResultadoPartido("FC Barcelona", "Villarreal CF", 3, 0, 0),
        ResultadoPartido("Sevilla FC", "Real Sociedad", 1, 1, 5)
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Resultados", color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Button(onClick = {}) {
                Text("Jornada")
                Icon(Icons.Default.KeyboardArrowDown, "Flecha")
            }
        }
        Text(
            "Resultados oficiales y puntos obtenidos",
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
            color = Color.White
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            resultados.forEach { ResultadoItem(it) }
        }
    }
}

@Composable
fun ResultadoItem(resultado: ResultadoPartido) {
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
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(start = 12.dp)
                .background(Color(0xFFE8F5E9), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Text("${resultado.puntos} pts", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
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
        Text("$goles", color = Color.Black, fontWeight = FontWeight.Bold)
    }
}