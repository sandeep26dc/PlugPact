package com.sandeep26dc.plugpact.ui.components

import android.graphics.RuntimeShader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeep26dc.plugpact.graphics.SPARK_SHADER_SRC
import kotlinx.coroutines.delay

@Composable
fun MicroSparkNode(percent: Int, isCharging: Boolean) {
    val shader = remember { RuntimeShader(SPARK_SHADER_SRC) }
    var time by remember { mutableFloatStateOf(0f) }

    // Dynamic Color Logic
    val sparkColor = when {
        percent >= 80 -> Color(0xFF00FF9D) // Emerald Green (Target Reached)
        isCharging -> Color(0xFF00F0FF)    // Electric Blue (Charging)
        else -> Color(0xFFFFFFFF)          // White (Idle)
    }

    LaunchedEffect(Unit) {
        while (true) {
            time += 0.05f
            shader.setFloatUniform("time", time)
            delay(16)
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(100.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            shader.setFloatUniform("resolution", size.width, size.height)
            drawCircle(brush = ShaderBrush(shader))
        }
        Text(
            text = "$percent%",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
