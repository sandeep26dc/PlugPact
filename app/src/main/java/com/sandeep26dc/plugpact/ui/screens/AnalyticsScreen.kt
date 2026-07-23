package com.sandeep26dc.plugpact.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeep26dc.plugpact.ui.components.GlassCard

@Composable
fun AnalyticsScreen() {
    Column(modifier = Modifier.padding(24.dp)) {
        Text("ANALYTICS", color = Color(0xFF00F0FF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("Voltage Stability", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(24.dp))
        
        GlassCard {
            Canvas(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                val path = Path().apply {
                    moveTo(0f, size.height * 0.7f)
                    cubicTo(size.width * 0.3f, size.height * 0.1f, size.width * 0.6f, size.height * 0.9f, size.width, size.height * 0.4f)
                }
                drawPath(
                    path = path,
                    color = Color(0xFF00F0FF),
                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                )
                drawPath(
                    path = path.apply {
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    },
                    brush = Brush.verticalGradient(listOf(Color(0x3300F0FF), Color.Transparent))
                )
            }
        }
    }
}
