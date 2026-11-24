package org.elCampico.PorraDam.screens.main.jornada

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.elCampico.PorraDam.data.model.Jornada
import org.elCampico.PorraDam.data.model.Partido
import org.elCampico.PorraDam.data.model.Porra
import org.elCampico.PorraDam.screens.main.resultados.calcularPuntos

class JornadasViewModel : ViewModel() {

    val partidosEnviados = mutableStateListOf<Partido>()
    var jornadas by mutableStateOf<List<Jornada>>(emptyList())
        private set
    var numeroJornada by mutableIntStateOf(0)
        private set

    fun cargar(lista: List<Jornada>) {
        jornadas = lista
        if (numeroJornada == 0) seleccionar(lista.first().numero)
    }

    fun seleccionar(numero: Int) {
        val jornada = jornadas.first { it.numero == numero }
        numeroJornada = numero
        partidosEnviados.clear()
        partidosEnviados.addAll(jornada.partidos)
    }

    private var cargado = false
    fun cargar(jornada: Jornada) {
        if (cargado) return     // Ya se cargó
        numeroJornada = jornada.numero
        partidosEnviados.addAll(jornada.partidos)
        cargado = true
    }

    fun construirPorras(
        usuarioId: Int,
        golesLocal: List<Int>,
        golesVisitante: List<Int>
    ): List<Porra> =
        partidosEnviados.mapIndexed { i, partido ->
            Porra(usuarioId, partido.id, golesLocal[i], golesVisitante[i])
        }
}