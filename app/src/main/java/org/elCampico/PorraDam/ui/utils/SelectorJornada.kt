package org.elCampico.PorraDam.ui.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.elCampico.PorraDam.data.model.Jornada
import kotlin.collections.forEach

@Composable
fun SelectorJornada(
    jornadas: List<Jornada>,
    seleccionada: Int?,
    onSeleccion: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }
    val forma = RoundedCornerShape(8.dp)

    Box(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.LightGray, forma)
                .clip(forma)
                .clickable { expandido = true }
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(if (seleccionada != null) "Jornada $seleccionada" else "Jornada", color = Color.Black)
            Icon(Icons.Default.KeyboardArrowDown, "Elegir jornada", tint = Color.Black)
        }
        DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
            jornadas.forEach { jornada ->
                DropdownMenuItem(
                    text = { Text("Jornada ${jornada.numero}") },
                    onClick = {
                        onSeleccion(jornada.numero)
                        expandido = false
                    }
                )
            }
        }
    }
}