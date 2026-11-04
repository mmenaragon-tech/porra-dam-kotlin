package org.elCampico.PorraDam.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import org.elCampico.PorraDam.R

private val Oscurecido = Brush.verticalGradient(
    listOf(Color.Black.copy(alpha = 0.3f), Color.Black.copy(alpha = 0.75f))
)

@Composable
fun BoxScope.FondoPantalla(modifier: Modifier = Modifier) {
    Image(
    painter = painterResource(R.drawable.fondo_pantalla_hd),
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = modifier.matchParentSize()
    )
    Box(Modifier.matchParentSize().background(Oscurecido))
}