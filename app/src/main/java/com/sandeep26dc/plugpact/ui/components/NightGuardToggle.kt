package com.sandeep26dc.plugpact.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeep26dc.plugpact.core.NightGuardManager

@Composable
fun NightGuardToggle() {
    val isEnabled by NightGuardManager.isAlarmEnabled.collectAsState()
    val glowColor by animateColorAsState(if (isEnabled) Color(0xFF00F0FF) else Color(0xFF1A1F26))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF0F141C))
            .clickable { NightGuardManager.isAlarmEnabled.value = !isEnabled },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // The "LED" Status light
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(RoundedCornerShape(50))
                    .background(glowColor)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text("NIGHT GUARD ALARM", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = if (isEnabled) "ACTIVE (NOTIFY AT 80%)" else "DEACTIVATED",
                    color = if (isEnabled) Color(0xFF00F0FF) else Color.Gray,
                    fontSize = 11.sp
                )
            }
        }
    }
}
