package com.texniti.ergasia

data class ScheduledLesson(
    val lesson: String,
    val professor: String,
    val semester: String,
    val department: String,
    val requiredRoomSize: RoomSize,
    val room: String,
    val roomSize: RoomSize,
    val professorUnavailableDays: Set<String> = emptySet()
)
