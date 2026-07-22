package com.sandeep26dc.plugpact.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object BatteryState {
    private val _currentData = MutableStateFlow(BatteryData(0, 0, 0))
    val currentData = _currentData.asStateFlow()

    fun update(data: BatteryData) {
        _currentData.value = data
    }
}
