package com.sandeep26dc.plugpact.core

import android.content.Context
import android.media.RingtoneManager
import kotlinx.coroutines.flow.MutableStateFlow

object NightGuardManager {
    val isAlarmEnabled = MutableStateFlow(false)
    private var hasFired = false

    fun checkAlarm(context: Context, percent: Int) {
        if (isAlarmEnabled.value && percent >= 80 && !hasFired) {
            triggerAlarm(context)
            hasFired = true
        } else if (percent < 80) {
            hasFired = false // Reset so it can fire again next time it hits 80
        }
    }

    private fun triggerAlarm(context: Context) {
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(context, notification)
        r.play()
    }
}
