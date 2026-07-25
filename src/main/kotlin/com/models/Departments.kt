package com.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Departments(
    @SerialName("department_name") val departmentName: String = ""
)
