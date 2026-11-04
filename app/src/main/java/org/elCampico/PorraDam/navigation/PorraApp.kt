package org.elCampico.PorraDam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.elCampico.PorraDam.navigation.PantallaPrincipal
import org.elCampico.PorraDam.screens.main.sala.PantallaBienvenida

@Composable
fun PorraApp(modifier: Modifier = Modifier) {
    var alias by rememberSaveable { mutableStateOf<String?>(null) }
    val actual = alias
    if(actual == null) {
        PantallaBienvenida(
            onEntrar = { nombre -> alias = nombre},
            modifier = modifier

        )
    } else {
        PantallaPrincipal(
            alias = actual,
            modifier = modifier
        )
    }
}