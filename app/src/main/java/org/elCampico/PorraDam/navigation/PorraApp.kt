package org.elCampico.PorraDam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.elCampico.PorraDam.model.leerColoresAvatar
import org.elCampico.PorraDam.model.leerSalas
import org.elCampico.PorraDam.model.leerUsuarios
import org.elCampico.PorraDam.navigation.PantallaPrincipal
import org.elCampico.PorraDam.screens.main.sala.PantallaBienvenida

@Composable
fun PorraApp(modifier: Modifier = Modifier) {
    var alias by rememberSaveable { mutableStateOf<String?>(null) }
    val contexto = LocalContext.current
    val salas = remember {
        val colores = leerColoresAvatar(contexto)
        val usuarios = leerUsuarios(contexto, colores)
        leerSalas(contexto, usuarios)
    }
    val actual = alias
    if(actual == null) {
        PantallaBienvenida(
            salas = salas,
            onEntrar = { nombre, sala -> alias = nombre },
            modifier = modifier
        )
    } else {
        PantallaPrincipal(
            alias = actual,
            modifier = modifier
        )
    }
}