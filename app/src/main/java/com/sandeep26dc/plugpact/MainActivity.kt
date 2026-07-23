package com.sandeep26dc.plugpact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.sandeep26dc.plugpact.service.SparkOverlayService
import com.sandeep26dc.plugpact.ui.components.GlassyPageIndicator
import com.sandeep26dc.plugpact.ui.screens.*
import com.sandeep26dc.plugpact.ui.theme.PlugPactTheme
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlugPactTheme {
                val pagerState = rememberPagerState(pageCount = { 3 })
                val haptic = LocalHapticFeedback.current

                // TRIGGER HAPTIC ON PAGE CHANGE
                LaunchedEffect(pagerState.currentPage) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }

                Scaffold(
                    containerColor = Color(0xFF05070A),
                    bottomBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            // THE NEW GLASSY PILL INDICATOR
                            GlassyPageIndicator(pagerState = pagerState, pageCount = 3)
                        }
                    }
                ) { padding ->
                    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                        HorizontalPager(state = pagerState) { page ->
                            when(page) {
                                0 -> OverviewScreen(onInitClick = { checkAndStartService() })
                                1 -> AnalyticsScreen()
                                2 -> AestheticsScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun checkAndStartService() {
        if (!Settings.canDrawOverlays(this)) {
            startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")))
        } else {
            startService(Intent(this, Intent(this, SparkOverlayService::class.java)))
        }
    }
}
