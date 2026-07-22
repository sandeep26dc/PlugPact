package com.sandeep26dc.plugpact.core

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.sandeep26dc.plugpact.R

class NotificationHelper(private val context: Context) {
    private val channelId = "plugpact_monitor"
    
    init {
        val channel = NotificationChannel(channelId, "Battery Monitor", NotificationManager.IMPORTANCE_LOW)
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    fun buildNotification(percent: Int, isCharging: Boolean): Notification {
        // Decide color based on state
        val statusText = if (isCharging) "⚡ Charging Active" else "Monitoring Battery"
        
        return NotificationCompat.Builder(context, channelId)
            .setContentTitle("PlugPact: $percent%")
            .setContentText(statusText)
            .setSmallIcon(android.R.drawable.ic_lock_idle_low_battery) // We will replace with custom blue spark later
            .setOngoing(true)
            .build()
    }
}
