package com.sandeep26dc.plugpact.service

import android.content.Intent
import android.content.IntentFilter
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.WindowManager
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.sandeep26dc.plugpact.core.*
import com.sandeep26dc.plugpact.ui.components.MicroSparkNode

class SparkOverlayService : LifecycleService() {

    private lateinit var windowManager: WindowManager
    private var overlayView: ComposeView? = null
    private lateinit var notificationHelper: NotificationHelper

    private val receiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: android.content.Context, intent: Intent) {
            val level = intent.getIntExtra(android.os.BatteryManager.EXTRA_LEVEL, -1)
            val voltage = intent.getIntExtra(android.os.BatteryManager.EXTRA_VOLTAGE, 0)
            val temp = intent.getIntExtra(android.os.BatteryManager.EXTRA_TEMPERATURE, 0) / 10
            val status = intent.getIntExtra(android.os.BatteryManager.EXTRA_STATUS, -1)
            val isCharging = status == android.os.BatteryManager.BATTERY_STATUS_CHARGING
            
            BatteryState.update(BatteryData(level, voltage, temp, isCharging))
            
            // Update the status bar notification dynamically
            val notification = notificationHelper.buildNotification(level, isCharging)
            val manager = getSystemService(android.app.NotificationManager::class.java)
            manager.notify(1, notification)
        }
    }

    override fun onCreate() {
        super.onCreate()
        notificationHelper = NotificationHelper(this)
        registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        
        // Make this a Foreground Service so it never dies
        startForeground(1, notificationHelper.buildNotification(0, false))
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (overlayView == null) showOverlay()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showOverlay() {
        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 30
            y = 120
        }

        overlayView = ComposeView(this).apply {
            setViewTreeLifecycleOwner(this@SparkOverlayService)
            setViewTreeSavedStateRegistryOwner(this@SparkOverlayService)
            setContent {
                val data = BatteryState.currentData.collectAsState()
                MicroSparkNode(percent = data.value.percent, isCharging = data.value.isCharging)
            }
        }
        windowManager.addView(overlayView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        overlayView?.let { windowManager.removeView(it) }
    }
}
