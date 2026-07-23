package com.sandeep26dc.plugpact.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeep26dc.plugpact.core.BatteryState
import com.sandeep26dc.plugpact.ui.components.CategoryTile
import com.sandeep26dc.plugpact.ui.components.InfometricTile

@Composable
fun OverviewScreen(onInitClick: () -> Unit) {
    val batteryData = BatteryState.currentData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("PLUGPACT", color = Color(0xFF00F0FF), fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
        Spacer(modifier = Modifier.height(30.dp))

        // INFOMETRIC GRID
        Row(modifier = Modifier.fillMaxWidth()) {
            InfometricTile("LEVEL", "${batteryData.value.percent}%", "⚡", Modifier.weight(1f))
            InfometricTile("TEMP", "${batteryData.value.temp}°C", "🌡️", Modifier.weight(1f))
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            InfometricTile("VOLTAGE", "${batteryData.value.voltage}mV", "🔌", Modifier.weight(1f))
            InfometricTile("HEALTH", "GOOD", "🛡️", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(30.dp))
        
        CategoryTile("NIGHT GUARD", "Healthy charge ceiling", "🔔")
        CategoryTile("OLED SHIELD", "Pixel-shift active", "🛡️")

        Spacer(modifier = Modifier.weight(1f))
        
        Button(
            onClick = onInitClick,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00F0FF))
        ) {
            Text("INITIALIZE HUD", color = Color.Black, fontWeight = FontWeight.Bold)
        }
    }
}
