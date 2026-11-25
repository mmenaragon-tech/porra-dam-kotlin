package org.elCampico.PorraDam.screens.main.clasificacion

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import org.elCampico.PorraDam.data.model.Usuario

class ClasificacionViewModel : ViewModel() {

    val usuariosEnviados = mutableStateListOf<Usuario>()

    init {
        usuariosEnviados
    }

    private var cargado=false
    fun cargar(ListaUsuarios: List<Usuario>){
        if (cargado) return     //Ya se cargó
        usuariosEnviados.addAll(ListaUsuarios)
        cargado=true
    }
}