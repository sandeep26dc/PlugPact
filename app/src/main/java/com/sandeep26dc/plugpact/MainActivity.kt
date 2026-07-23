package com.sandeep26dc.plugpact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
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
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlugPactTheme {
                val pagerState = rememberPagerState(pageCount = { 3 })
                val haptic = LocalHapticFeedback.current
                var isInitialLoad by remember { mutableStateOf(true) }

                LaunchedEffect(pagerState.currentPage) {
                    if (!isInitialLoad) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                    isInitialLoad = false
                }

                Scaffold(
                    containerColor = Color(0xFF05070A),
                    bottomBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 34.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            GlassyPageIndicator(pagerState = pagerState, pageCount = 3)
                        }
                    }
                ) { padding ->
                    Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
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

    private fun checkAndStartService() {
        if (!Settings.canDrawOverlays(this)) {
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
            startActivity(intent)
        } else {
            startService(Intent(this, SparkOverlayService::class.java))
        }
    }
}
