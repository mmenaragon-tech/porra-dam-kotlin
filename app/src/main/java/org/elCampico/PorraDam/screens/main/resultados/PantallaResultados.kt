package org.elCampico.PorraDam.screens.main.resultados

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.elCampico.PorraDam.data.model.Equipo
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.Usuario.Companion.USUARIO_ACTUAL
import org.elCampico.PorraDam.model.leerEquipos
import org.elCampico.PorraDam.model.leerPartidos
import org.elCampico.PorraDam.model.leerPorras
import org.elCampico.PorraDam.model.leerPorrasPartido
import org.elCampico.PorraDam.model.leerResultados
import org.elCampico.PorraDam.ui.theme.PorraDamTheme
import org.elCampico.PorraDam.model.leerJornadas
import org.elCampico.PorraDam.ui.utils.SelectorJornada

@Composable
fun PantallaResultados(
    modifier: Modifier = Modifier,
    vm: ResultadosViewModel = viewModel()
) {

    val contexto = LocalContext.current

    LaunchedEffect(Unit) {
        val (jornadas, porrasPartido) = withContext(Dispatchers.IO) {
            val equipos = leerEquipos(contexto)
            val jornadas = leerJornadas(contexto, equipos)
            val partidos = leerPartidos(jornadas)
            val resultados = leerResultados(contexto, partidos)
            val porras = leerPorras(contexto)
            jornadas to leerPorrasPartido(resultados, porras, USUARIO_ACTUAL)
        }
        vm.cargar(jornadas, porrasPartido)
    }

    val jornadas by vm.jornadas.collectAsState()
    val seleccionada by vm.seleccionada.collectAsState()

    val porras by vm.porrasEnviadas.collectAsState()
    val totalPuntos by vm.totalPuntos.collectAsState()

    val colorPuntos = when {
        totalPuntos > 0 -> Color.Green
        totalPuntos < 0 -> Color.Red
        else -> Color.Black
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
            SelectorJornada(jornadas, seleccionada, onSeleccion = vm::seleccionar)
        }
        Text(
            "Resultados oficiales y puntos obtenidos",
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp),
            color = Color.Black
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
                .padding(6.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(porras) { porraPartido ->
                ResultadoPartido(porraPartido)
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
fun ResultadoPartido(porraPartido: PorraPartido) {
    val resultado = porraPartido.resultado
    val colorPrincipal: Color
    val colorSecundario: Color
    val puntos: Int = calcularPuntos(porraPartido)
    if (puntos == 8) {
        colorPrincipal = Color.Green
        colorSecundario = Color(0xFFE8F5E9)
    } else if (puntos == 0) {
        colorPrincipal = Color.Red
        colorSecundario = Color(0xFFFFEBEE)
    } else {
        colorPrincipal = Color.Black
        colorSecundario = Color.LightGray
    }
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
            FilaResultado(resultado.partido.local, resultado.golesLocal)
            FilaResultado(resultado.partido.visitante, resultado.golesVisitante)
            Text(
                "Tu porra: ${porraPartido.porra.golesLocal} - ${porraPartido.porra.golesVisitante}",
                color = colorPrincipal,
                fontSize = 12.sp,
                modifier = Modifier
                    .background(colorSecundario, RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.Top)
                .padding(start = 20.dp, top = 12.dp)
                .background(colorSecundario, RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                "+$puntos",
                color = colorPrincipal,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun FilaResultado(equipo: Equipo, goles: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier.size(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = equipo.escudo),
                contentDescription = "Escudo de ${equipo.nombre}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Text(equipo.nombre, color = Color.Black)
        Text(
            "$goles",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }

}

fun calcularPuntos(porraPartido: PorraPartido): Int {
    // 1 = gana local, 0 = empate, -1 = gana visitante
    val signoResultado = porraPartido.resultado.golesLocal.compareTo(porraPartido.resultado.golesVisitante)
    val signoPorra = porraPartido.porra.golesLocal.compareTo(porraPartido.porra.golesVisitante)

    return if (porraPartido.resultado.golesLocal == porraPartido.porra.golesLocal &&
        porraPartido.resultado.golesVisitante == porraPartido.porra.golesVisitante
    ) {
        8
    } else if (signoResultado == signoPorra) {
        4
    } else if (porraPartido.resultado.golesLocal == porraPartido.porra.golesLocal) {
        2
    } else {
        0
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaResultadosPreview() {
    PorraDamTheme {
        PantallaResultados()
    }
}