package com.sandeep26dc.plugpact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sandeep26dc.plugpact.service.SparkOverlayService
import com.sandeep26dc.plugpact.ui.theme.PlugPactTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlugPactTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "PLUGPACT",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        
                        Button(
                            onClick = { checkAndStartService() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("ACTIVATE SPARK HUD")
                        }
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
