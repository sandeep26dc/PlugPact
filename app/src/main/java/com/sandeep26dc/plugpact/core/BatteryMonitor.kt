package com.sandeep26dc.plugpact.core

enum class HUDState {
    CHARGING_BLUE,   // Electric Spark (Active)
    LOW_RED,         // Pulsing Warning (Critical < 20%)
    FULL_GREEN,      // Breathing Serenity (Target Reached)
    IDLE_WHITE       // Minimalist (Standard Discharging)
}

data class BatteryData(
    val percent: Int,
    val voltage: Int,
    val temp: Int,
    val isCharging: Boolean = false
) {
    val hudState: HUDState
        get() = when {
            isCharging -> HUDState.CHARGING_BLUE
            percent < 20 -> HUDState.LOW_RED
            percent >= 80 -> HUDState.FULL_GREEN
            else -> HUDState.IDLE_WHITE
        }
}
