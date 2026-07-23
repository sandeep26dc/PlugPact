package com.sandeep26dc.plugpact.ui.components

import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeep26dc.plugpact.core.HUDState
import com.sandeep26dc.plugpact.graphics.SPARK_SHADER_SRC
import kotlinx.coroutines.delay

@Composable
fun MicroSparkNode(percent: Int, state: HUDState) {
    val infiniteTransition = rememberInfiniteTransition(label = "ZenLoop")
    
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f, targetValue = 1.05f,
        animationSpec = infiniteRepeatable(tween(2000, easing = EaseInOutSine), RepeatMode.Reverse),
        label = "Pulse"
    )

    val activeColor by animateColorAsState(
        targetValue = when (state) {
            HUDState.CHARGING_BLUE -> Color(0xFF00F0FF)
            HUDState.LOW_RED -> Color(0xFFFF3B30)
            HUDState.FULL_GREEN -> Color(0xFF00FF9D)
            HUDState.IDLE_WHITE -> Color.White
        },
        animationSpec = tween(1000), label = "ColorShift"
    )

    Box(
        contentAlignment = Alignment.Center, 
        modifier = Modifier.size(100.dp).scale(if (state != HUDState.CHARGING_BLUE) pulseScale else 1f)
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && state == HUDState.CHARGING_BLUE) {
            val shader = remember { RuntimeShader(SPARK_SHADER_SRC) }
            var time by remember { mutableFloatStateOf(0f) }
            LaunchedEffect(Unit) {
                while (true) {
                    time += 0.04f
                    shader.setFloatUniform("time", time)
                    delay(16)
                }
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                shader.setFloatUniform("resolution", size.width, size.height)
                drawCircle(brush = ShaderBrush(shader))
            }
        } else {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = activeColor,
                    style = Stroke(width = 2.dp.toPx()),
                    alpha = if (state == HUDState.IDLE_WHITE) 0.4f else 0.8f
                )
            }
        }
        
        Text(
            text = "$percent%",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
