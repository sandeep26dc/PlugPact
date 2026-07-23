package com.sandeep26dc.plugpact.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatingCore(onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.15f,
        animationSpec = infiniteRepeatable(tween(2000), RepeatMode.Reverse)
    )

    Box(
        modifier = Modifier
            .size(60.dp)
            .scale(pulse)
            .clip(CircleShape)
            .background(Brush.radialGradient(listOf(Color(0xFF00F0FF), Color(0xFF0080FF))))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Inner Core
        Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFF05070A)))
    }
}
