package org.elCampico.PorraDam.screens.main.clasificacion

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Usuario(
    val nombre: String,
    val posicion: Int,
    val puntos: Int
)
@Composable
fun PantallaClasificacion(
    modifier: Modifier = Modifier
) {
    val usuarios = listOf(
        Usuario("LUCÍA R.", 1, 45),
        Usuario("PabloDev", 2, 41),
        Usuario("Nerea_K", 3, 40),
        Usuario("Tú · mcoma", 4, 38),
        Usuario("Hugo M.", 5, 35),
        Usuario("Aitana", 6, 33),
        Usuario("xavier.dev", 7, 30),
        Usuario("Iván G.", 8, 27),
        Usuario("Sara P.", 9, 26),
        Usuario("Dani_804", 10, 19)
    )
    Column(
        modifier = modifier.fillMaxSize()
            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .background(Color.White)
            .padding(vertical = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Clasificación", color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text("General")
                Icon(Icons.Default.KeyboardArrowDown, "Flecha")
            }
        }
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 18.dp, vertical = 3.dp),
        ) {
            Text(
                "#",
                Modifier.padding(horizontal = 30.dp)
            )
            Text(
                "Participante",
                Modifier.padding(horizontal = 10.dp).weight(1f)
            )
            Text(
                "Puntos",
                Modifier.padding(horizontal = 25.dp)
            )
        }
        LazyColumn(

        ) {
            items(usuarios, key = { it.posicion }) { usuario ->
                BarraUsuario(usuario)
            }
        }
    }
}

@Composable
fun BarraUsuario(usuario: Usuario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Posicion(usuario.posicion)
        Avatar()
        Text(
            usuario.nombre,
            Modifier.padding(horizontal = 10.dp).weight(1f)
        )
        if (usuario.posicion == 1) {
            Icon(
                Icons.Default.WorkspacePremium,   // o EmojiEvents, MilitaryTech
                contentDescription = "Primer puesto",
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            "${usuario.puntos}",
            Modifier.padding(horizontal = 20.dp),
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
fun Posicion(posicion: Int, modifier: Modifier = Modifier) {
    // Fondo, borde y texto de cada medalla (null si no es del podio)
    val colores = when (posicion) {
        1 -> Triple(Color(0xFFFFF3C4), Color(0xFFFFC107), Color(0xFFB8860B)) // oro
        2 -> Triple(Color(0xFFF0F0F0), Color(0xFFB0B0B0), Color(0xFF757575)) // plata
        3 -> Triple(Color(0xFFFFE0C2), Color(0xFFCD7F32), Color(0xFF8D5524)) // bronce
        else -> null
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(horizontal = 22.dp)
            .size(28.dp)
            .then(
                if (colores != null) {
                    Modifier
                        .background(colores.first, CircleShape)
                        .border(1.5.dp, colores.second, CircleShape)
                } else Modifier
            )
    ) {
        Text(
            "$posicion",
            color = colores?.third ?: Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Avatar(
    nombre: String,
    colorFondo: Color,
    colorLetra: Color,
    modifier: Modifier = Modifier,
    @DrawableRes foto: Int? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(colorFondo)
    ) {
        if (foto != null) {
            Image(
                painter = painterResource(foto),
                contentDescription = nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Text(
                nombre.first().uppercase(),
                color = colorLetra,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}