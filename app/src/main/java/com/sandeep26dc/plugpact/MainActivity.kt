package com.sandeep26dc.plugpact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlugPactTheme {
                // Initialize Pager with 3 screens: Overview, Analytics, Aesthetics
                val pagerState = rememberPagerState(pageCount = { 3 })
                val haptic = LocalHapticFeedback.current
                
                // Track if the app has just launched to prevent an initial haptic buzz
                var isInitialLoad by remember { mutableStateOf(true) }

                // THE HAPTIC WHISPER: Only triggers when the user swerves between "Worlds"
                LaunchedEffect(pagerState.currentPage) {
                    if (!isInitialLoad) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                    isInitialLoad = false
                }

                Scaffold(
                    containerColor = Color(0xFF05070A),
                    bottomBar = {
                        // ZEN NAVIGATION: The adaptive glassy pill indicator
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 34.dp), // Increased padding for elite spacing
                            contentAlignment = Alignment.Center
                        ) {
                            GlassyPageIndicator(pagerState = pagerState, pageCount = 3)
                        }
                    }
                ) { padding ->
                    // THE CORE ENGINE: Horizontal navigation between bespoke modules
                    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                            beyondViewportPageCount = 1 // Keeps the "World" next door ready for a smooth swipe
                        ) { page ->
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

    /**
     * SECURE SERVICE INITIALIZATION
     * Handles the System Overlay permission and starts the Spark HUD Engine.
     */
    private fun checkAndStartService() {
        if (!Settings.canDrawOverlays(this)) {
            // Requesting system permission for the Floating HUD
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION, 
                Uri.parse("package:$packageName")
            )
            startActivity(intent)
        } else {
            // FIX: Corrected Intent logic to prevent double-nesting and ensure clean start
            val serviceIntent = Intent(this, SparkOverlayService::class.java)
            startService(serviceIntent)
        }
    }
}
