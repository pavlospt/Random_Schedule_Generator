package com.texniti.ergasia

data class LessonObject(
    var professor: String = "",
    var semester: String = "",
    var requiredRoomSize: String = "",
    var department: String = "",
    var room: String = "",
    var lessonName: String = ""
) {
    override fun toString(): String = lessonName
}
