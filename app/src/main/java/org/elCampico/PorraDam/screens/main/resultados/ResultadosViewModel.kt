package org.elCampico.PorraDam.screens.main.resultados

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.elCampico.PorraDam.data.model.Jornada
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.ResultadoPartido

class ResultadosViewModel : ViewModel() {

    private val todas = MutableStateFlow<List<PorraPartido>>(emptyList())
    private val _jornadas = MutableStateFlow<List<Jornada>>(emptyList())
    private val _seleccionada = MutableStateFlow<Int?>(null)

    val jornadas: StateFlow<List<Jornada>> = _jornadas.asStateFlow()
    val seleccionada: StateFlow<Int?> = _seleccionada.asStateFlow()

    val porrasEnviadas: StateFlow<List<PorraPartido>> =
        combine(todas, _jornadas, _seleccionada) { porras, jornadas, numero ->
            val ids = jornadas.firstOrNull { it.numero == numero }
                ?.partidos?.map { it.id }?.toSet().orEmpty()
            porras.filter { it.resultado.partido.id in ids }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val totalPuntos: StateFlow<Int> = porrasEnviadas
        .map { lista -> lista.sumOf { calcularPuntos(it) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    fun cargar(jornadas: List<Jornada>, porras: List<PorraPartido>) {
        _jornadas.value = jornadas
        todas.value = porras
        if (_seleccionada.value == null) _seleccionada.value = jornadas.firstOrNull()?.numero
    }

    fun seleccionar(numero: Int) { _seleccionada.value = numero }
}