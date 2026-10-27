package org.elCampico.PorraDam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.elCampico.PorraDam.navigation.PorraApp
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