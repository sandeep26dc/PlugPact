package com.sandeep26dc.plugpact.ui.components

import android.graphics.RuntimeShader
import android.os.Build
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
    // Pixel Shifting Logic (Anti-Burn-In)
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    
    LaunchedEffect(Unit) {
        while (true) {
            delay(60000) // Move every 1 minute
            offsetX = (-2..2).random().toFloat()
            offsetY = (-2..2).random().toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center, 
        modifier = Modifier.size(100.dp).offset(offsetX.dp, offsetY.dp)
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // HIGH-END SHADER (Android 13+)
            val shader = remember { RuntimeShader(SPARK_SHADER_SRC) }
            var time by remember { mutableFloatStateOf(0f) }
            LaunchedEffect(Unit) {
                while (true) {
                    time += 0.05f
                    shader.setFloatUniform("time", time)
                    delay(16)
                }
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                shader.setFloatUniform("resolution", size.width, size.height)
                drawCircle(brush = ShaderBrush(shader))
            }
        } else {
            // PREMIUM FALLBACK (For Older Androids)
            Box(modifier = Modifier.fillMaxSize().border(2.dp, Color(0xFF00F0FF), CircleShape))
        }
        
        Text("$percent%", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
