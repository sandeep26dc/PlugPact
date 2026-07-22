package com.sandeep26dc.plugpact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandeep26dc.plugpact.core.BatteryState
import com.sandeep26dc.plugpact.service.SparkOverlayService
import com.sandeep26dc.plugpact.ui.components.GlassCard
import com.sandeep26dc.plugpact.ui.theme.PlugPactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlugPactTheme {
                val batteryData = BatteryState.currentData.collectAsState()

                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF05070A)) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "PLUGPACT",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF00F0FF)
                        )
                        Text(
                            text = "Executive Battery Health",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // TAMM-Style Premium Health Card
                        GlassCard {
                            Text("CORE TELEMETRY", fontSize = 12.sp, color = Color(0xFF00F0FF), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Column {
                                    Text("BATTERY LEVEL", fontSize = 10.sp, color = Color.LightGray)
                                    Text("${batteryData.value.percent}%", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                                Column {
                                    Text("TEMP", fontSize = 10.sp, color = Color.LightGray)
                                    Text("${batteryData.value.temp}°C", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                                Column {
                                    Text("VOLTAGE", fontSize = 10.sp, color = Color.LightGray)
                                    Text("${batteryData.value.voltage}mV", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { checkAndStartService() },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00F0FF))
                        ) {
                            Text("ACTIVATE SPARK HUD", color = Color.Black, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
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
