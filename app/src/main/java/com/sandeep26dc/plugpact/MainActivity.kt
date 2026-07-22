package com.sandeep26dc.plugpact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sandeep26dc.plugpact.core.BatteryState
import com.sandeep26dc.plugpact.service.SparkOverlayService
import com.sandeep26dc.plugpact.ui.components.*
import com.sandeep26dc.plugpact.ui.theme.PlugPactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlugPactTheme {
                val batteryData = BatteryState.currentData.collectAsState()
                var showDialog by remember { mutableStateOf(false) }

                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF05070A)) {
                    Column(
                        modifier = Modifier.padding(24.dp).verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CreatorCard() // Professional Credits
                        
                        Spacer(modifier = Modifier.height(40.dp))
                        
                        // THE MASTER TAMM BUTTON
                        MasterBatteryButton(onClick = { showDialog = true })
                        
                        Spacer(modifier = Modifier.height(40.dp))

                        NightGuardToggle()
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Button(
                            onClick = { checkAndStartService() },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00F0FF))
                        ) {
                            Text("ACTIVATE SPARK HUD", color = Color.Black)
                        }
                    }
                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        containerColor = Color(0xFF0F141C),
                        title = { Text("CORE DIAGNOSTICS", color = Color(0xFF00F0FF)) },
                        text = {
                            Column {
                                Text("Level: ${batteryData.value.percent}%", color = Color.White)
                                Text("Voltage: ${batteryData.value.voltage}mV", color = Color.White)
                                Text("Temp: ${batteryData.value.temp}°C", color = Color.White)
                                Text("Status: ${if (batteryData.value.isCharging) "Charging" else "Discharging"}", color = Color.White)
                            }
                        },
                        confirmButton = { TextButton(onClick = { showDialog = false }) { Text("CLOSE") } }
                    )
                }
            }
        }
    }

    private fun checkAndStartService() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivity(intent)
        } else {
            startService(Intent(this, SparkOverlayService::class.java))
        }
    }
}
