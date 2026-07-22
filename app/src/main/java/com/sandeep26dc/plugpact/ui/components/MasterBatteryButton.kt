package com.sandeep26dc.plugpact.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleFeature
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MasterBatteryButton(onClick: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    
    // Floating animation logic
    val floatAnim by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = 10f,
        animationSpec = infiniteRepeatable(animation = tween(2000), repeatMode = RepeatMode.Reverse)
    )

    Box(
        modifier = Modifier
            .offset(y = floatAnim.dp)
            .size(160.dp)
            .shadow(30.dp, CircleShape, ambientColor = Color(0xFF00F0FF), spotColor = Color(0xFF00F0FF))
            .clip(CircleShape)
            .background(Brush.radialGradient(listOf(Color(0xFF131B26), Color(0xFF05070A))))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("TAP FOR", fontSize = 10.sp, color = Color.Gray)
            Text("DIAGNOSTICS", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF00F0FF))
        }
    }
}
