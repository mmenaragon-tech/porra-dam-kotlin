package org.elCampico.PorraDam.screens.main.jornada

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.elCampico.PorraDam.data.model.Partido
import org.elCampico.PorraDam.screens.main.resultados.calcularPuntos

class JornadasViewModel : ViewModel() {

    val partidosEnviados = mutableStateListOf<Partido>()

    init {
        partidosEnviados
    }

    private var cargado=false
    fun cargar(ListaPartidos: List<Partido>){
        if (cargado) return     //Ya se cargó
        partidosEnviados.addAll(ListaPartidos)
        cargado=true
    }
}