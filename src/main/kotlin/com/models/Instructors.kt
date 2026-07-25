package com.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Instructors(
    @SerialName("instructor") val instructor: String = "",
    @SerialName("kwdikos") val kwdikos: String = "",
    @SerialName("non_available") val nonAvailable: String = ""
)
