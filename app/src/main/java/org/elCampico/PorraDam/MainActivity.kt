package org.elCampico.PorraDam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.elCampico.PorraDam.navigation.PorraApp
import org.elCampico.PorraDam.screens.main.Formulario
import org.elCampico.PorraDam.screens.main.Listas
import org.elCampico.PorraDam.screens.main.inicio.Inicio
import org.elCampico.PorraDam.ui.theme.PorraDamTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PorraDamTheme {

                PorraApp()


            }
        }
    }

}