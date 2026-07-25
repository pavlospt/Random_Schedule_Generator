package com.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Slots(
    @SerialName("calendar") val calendar: String = "",
    @SerialName("day_name") val dayName: String = "",
    @SerialName("available_hours") val availableHours: String = ""
)
