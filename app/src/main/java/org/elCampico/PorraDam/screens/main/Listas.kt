package org.elCampico.PorraDam.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Listas(modifier: Modifier = Modifier) {
    val partidos = listOf<String>("Partido 1", "Partido 2")

    LazyColumn(modifier = modifier) {
        item {
            Text("Jornada 1")
        }
        items(partidos.size, key = {it}) {
            /*partido => Text(partido)*/
        }
    }
}