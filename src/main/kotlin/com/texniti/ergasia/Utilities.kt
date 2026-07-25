package com.texniti.ergasia

import com.models.Courses
import com.models.Rooms
import com.models.Slots

fun findAvailableDayFromMonth(i: Int, slots: List<Slots>): String {
    val slotIndex = i.mod(5)
    val calendar = slots[slotIndex].calendar.split(",")
    return calendar[i / 5]
}

fun createDayTimeSlots(slots: List<Slots>): List<DayTimeSlot> =
    buildList {
        for (i in 0 until slots.size * 4) {
            for (j in 0..2) {
                add(
                    DayTimeSlot(
                        day = DayOfWeek.fromIndex(i).label,
                        time = TimeSlot.fromIndex(j).label,
                        dayFromMonth = findAvailableDayFromMonth(i, slots)
                    )
                )
            }
        }
    }

fun fillLessons(courses: List<Courses>, rooms: List<Rooms>): List<LessonObject> =
    courses.map { course ->
        val randomRoom = rooms.random()
        LessonObject(
            lessonName = course.courseName,
            department = course.department,
            professor = course.instructor,
            semester = course.semester,
            requiredRoomSize = course.requiredClassSize,
            room = randomRoom.roomName
        )
    }

fun fillExamDayArray(totalSlots: Int, slots: List<Slots>): List<ExamDay> =
    buildList {
        for (i in 0 until totalSlots) {
            for (j in 0..2) {
                add(
                    ExamDay(
                        time = TimeSlot.fromIndex(j),
                        day = DayOfWeek.fromIndex(i),
                        dayFromMonth = findAvailableDayFromMonth(i, slots)
                    )
                )
            }
        }
    }
