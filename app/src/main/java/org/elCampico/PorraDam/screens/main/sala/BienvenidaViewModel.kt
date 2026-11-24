package org.elCampico.PorraDam.screens.main.sala

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.elCampico.PorraDam.data.model.Sala

class BienvenidaViewModel(
    private val salas: List<Sala>
) : ViewModel() {

    var alias by mutableStateOf("")
        private set

    var sala by mutableStateOf("")
        private set

    val aliasValido: Boolean
        get() = alias.isNotBlank()

    val salaValida: Boolean
        get() = sala.isNotBlank() && salas.any { it.nombre.equals(sala.trim(), ignoreCase = true) }

    val puedeEntrar: Boolean
        get() = aliasValido && salaValida

    fun onAliasChange(nuevo: String) {
        alias = nuevo
    }

    fun onSalaChange(nueva: String) {
        sala = nueva
    }

    fun entrar(onEntrar: (alias: String, sala: Sala) -> Unit) {
        if (!puedeEntrar) return
        val salaEncontrada = salas.first { it.nombre.equals(sala.trim(), ignoreCase = true) }
        onEntrar(alias.trim(), salaEncontrada)
    }
}