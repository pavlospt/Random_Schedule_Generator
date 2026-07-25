package com.texniti.ergasia

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ExamDayTest {

    @Test
    fun `copyTemplate creates ExamDay with same metadata but no lessons`() {
        val day = ExamDay(
            time = TimeSlot.SLOT_9_12,
            day = DayOfWeek.MONDAY,
            dayFromMonth = "2"
        )
        day.addLesson(
            ScheduledLesson(
                lesson = "Java",
                professor = "Kuriazis",
                semester = "1",
                department = "Plhroforikh",
                requiredRoomSize = RoomSize.SMALL,
                room = "A1",
                roomSize = RoomSize.SMALL
            )
        )

        val copy = day.copyTemplate()
        assertEquals(TimeSlot.SLOT_9_12, copy.time)
        assertEquals(DayOfWeek.MONDAY, copy.day)
        assertEquals("2", copy.dayFromMonth)
        assertEquals(0, copy.lessonCount, "Template copy should have no lessons")
    }

    @Test
    fun `lessonCount and property accessors work`() {
        val day = ExamDay(TimeSlot.SLOT_12_15, DayOfWeek.WEDNESDAY, "18")
        assertEquals(0, day.lessonCount)

        day.addLesson(
            ScheduledLesson("L1", "P1", "1", "D1", RoomSize.SMALL, "R1", RoomSize.SMALL)
        )
        day.addLesson(
            ScheduledLesson("L2", "P2", "3", "D2", RoomSize.LARGE, "R2", RoomSize.LARGE)
        )

        assertEquals(2, day.lessonCount)
        assertEquals(listOf("R1", "R2"), day.rooms)
        assertEquals(listOf("D1", "D2"), day.departments)
        assertEquals(listOf("1", "3"), day.semesters)
    }

    @Test
    fun `isProfessorUnavailable checks correctly`() {
        val day = ExamDay(TimeSlot.SLOT_9_12, DayOfWeek.MONDAY, "2")
        day.addLesson(
            ScheduledLesson(
                lesson = "L",
                professor = "P",
                semester = "1",
                department = "D",
                requiredRoomSize = RoomSize.SMALL,
                room = "R",
                roomSize = RoomSize.SMALL,
                professorUnavailableDays = setOf("2", "9")
            )
        )

        assertTrue(day.isProfessorUnavailable(0, "2"))
        assertTrue(day.isProfessorUnavailable(0, "9"))
        assertTrue(!day.isProfessorUnavailable(0, "3"))
    }
}
