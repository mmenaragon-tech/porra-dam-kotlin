package org.elCampico.PorraDam.screens.main.resultados

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.ResultadoPartido

class ResultadosViewModel : ViewModel() {

    private val _porrasEnviadas = MutableStateFlow<List<PorraPartido>>(emptyList())
    val porrasEnviadas: StateFlow<List<PorraPartido>> = _porrasEnviadas.asStateFlow()

    val totalPuntos: StateFlow<Int> = _porrasEnviadas
        .map { lista -> lista.sumOf { calcularPuntos(it) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0)

    // Sustituye la lista entera (sin flag "cargado")
    fun cargar(lista: List<PorraPartido>) {
        _porrasEnviadas.value = lista
    }

    // Para llamar justo al guardar una porra nueva
    fun agregar(porra: PorraPartido) {
        _porrasEnviadas.update { it + porra }
    }
}