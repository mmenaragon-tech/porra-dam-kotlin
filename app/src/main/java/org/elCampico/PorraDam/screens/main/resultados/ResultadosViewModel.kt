package org.elCampico.PorraDam.screens.main.resultados

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.elCampico.PorraDam.data.model.PorraPartido
import org.elCampico.PorraDam.data.model.ResultadoPartido

class ResultadosViewModel : ViewModel() {
    val porrasEnviadas = mutableStateListOf<PorraPartido>()

    fun calcularTotalPuntos(porras: List<PorraPartido>): Int =
        porras.sumOf { calcularPuntos(it) }

    init {
        porrasEnviadas
    }

    private var cargado=false
    fun cargar(ListaPorras: List<PorraPartido>){
        if (cargado) return     //Ya se cargó
        porrasEnviadas.addAll(ListaPorras)
        cargado=true
    }



}