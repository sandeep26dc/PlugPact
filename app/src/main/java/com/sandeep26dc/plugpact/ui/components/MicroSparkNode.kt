package com.sandeep26dc.plugpact.ui.components

import android.graphics.RuntimeShader
import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MicroSparkNode(percent: Int) {
    val shader = remember { RuntimeShader(SPARK_SHADER_SRC) }
    var time by remember { mutableFloatStateOf(0f) }

    // Animation loop for the spark
    LaunchedEffect(Unit) {
        while (true) {
            time += 0.05f
            shader.setFloatUniform("time", time)
            delay(16) // ~60 FPS
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.size(120.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            shader.setFloatUniform("resolution", size.width, size.height)
            drawCircle(brush = ShaderBrush(shader))
        }
        
        // The Battery Percentage in the middle
        Text(
            text = "$percent%",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
