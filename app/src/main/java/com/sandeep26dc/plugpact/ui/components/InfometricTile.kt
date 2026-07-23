package com.sandeep26dc.plugpact.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfometricTile(label: String, value: String, icon: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(6.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF0F141C))
            .border(1.dp, Color(0x1AFFFFFF), RoundedCornerShape(20.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(label, fontSize = 10.sp, color = Color.Gray)
                Text(icon, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}
