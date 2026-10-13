package org.elCampico.PorraDam

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.elCampico.PorraDam.ui.theme.PorraDamTheme
import org.elCampico.PorraDam.ui.auth.login.PantallaLogin
import org.elCampico.PorraDam.ui.auth.register.PantallaRegistro

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // fondo bajo la barra de estado
        setContent {
            PorraDamTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        PantallaLogin(
                            onEntrar = { usuario, _ ->
                                Toast.makeText(
                                    this@MainActivity, "Hola, $usuario", Toast.LENGTH_SHORT
                                ).show()
                            },
                            onIrRegistro = { navController.navigate("registro") }
                        )
                    }
                    composable("registro") {
                        PantallaRegistro(
                            onCrear = { usuario, _, _ ->
                                Toast.makeText(
                                    this@MainActivity, "Hola, $usuario", Toast.LENGTH_SHORT
                                ).show()
                            },
                            onIrALogin = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }

}