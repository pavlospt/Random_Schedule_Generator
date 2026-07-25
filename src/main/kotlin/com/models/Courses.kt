package com.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Courses(
    @SerialName("semester") val semester: String = "",
    @SerialName("course_name") val courseName: String = "",
    @SerialName("instructor") val instructor: String = "",
    @SerialName("required_class_size") val requiredClassSize: String = "",
    @SerialName("department") val department: String = "",
    @SerialName("course_code") val courseCode: String = ""
)
