package org.elCampico.PorraDam.screens.main

import android.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun Formulario(modifier: Modifier = Modifier) {
    var nombre by rememberSaveable() { mutableStateOf("") }
    var clave by rememberSaveable() { mutableStateOf("") }
    var visible by rememberSaveable() { mutableStateOf(false) }
    var contrasenya: KeyboardType
    if (visible) {
        contrasenya = KeyboardType.Password
    } else {
        contrasenya = KeyboardType.PasswordVisible
    }

    //var icono by rememberSaveable() { mutableStateOf(Icon(Icons.Default.RemoveRedEye)) }
    Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text("Nombre") },
                supportingText = { Text("Escribe tu nombre en mayúsculas") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Icono de usuario"
                    )
                }
            )
            OutlinedTextField(
                value = clave,
                onValueChange = { clave = it },
                label = { Text("Clave") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                placeholder = { Text("Clave") },
                supportingText = { Text("Escribe tu clave") },
                trailingIcon = {
                    IconButton(
                        onClick = {visible = !(visible)}
                    ) {
                        Icon(Icons.Default.RemoveRedEye, "ojo")
                    }
                }
            )
            Button(
                onClick = {
                    print(nombre)
                }
            ) {
                Text("Enviar nombre")
            }
        }
    }
}
