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
// IMPORTANT: Ensure this exact import matches your GlassCard.kt package
import com.sandeep26dc.plugpact.ui.components.GlassCard

@Composable
fun AnalyticsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "ANALYTICS", 
            color = Color(0xFF00F0FF), 
            fontSize = 12.sp, 
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
        Text(
            text = "Voltage Stability", 
            color = Color.White, 
            fontSize = 24.sp, 
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Premium Infographic Card
        GlassCard {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                val path = Path().apply {
                    moveTo(0f, size.height * 0.7f)
                    cubicTo(
                        size.width * 0.3f, size.height * 0.1f, 
                        size.width * 0.6f, size.height * 0.9f, 
                        size.width, size.height * 0.4f
                    )
                }

                // Cyber Line
                drawPath(
                    path = path,
                    color = Color(0xFF00F0FF),
                    style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                )

                // Bottom Gradient Glow
                val fillPath = android.graphics.Path(path.asAndroidPath())
                fillPath.lineTo(size.width, size.height)
                fillPath.lineTo(0f, size.height)
                fillPath.close()
                
                drawPath(
                    path = fillPath.asComposePath(),
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0x4400F0FF), Color.Transparent)
                    )
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "THERMAL DYNAMICS", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Text(text = "System monitoring active.", color = Color.Gray, fontSize = 12.sp)
    }
}
