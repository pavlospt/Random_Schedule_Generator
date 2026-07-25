package com.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rooms(
    @SerialName("room_code") val roomCode: String = "",
    @SerialName("room_name") val roomName: String = "",
    @SerialName("room_space") val roomSpace: String = ""
)
