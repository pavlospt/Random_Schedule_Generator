package com.texniti.ergasia

import kotlin.test.Test
import kotlin.test.assertTrue

class StateConstraintTest {

    private fun createExamDay(
        time: TimeSlot = TimeSlot.SLOT_9_12,
        day: DayOfWeek = DayOfWeek.MONDAY,
        dayFromMonth: String = "2"
    ): ExamDay = ExamDay(time, day, dayFromMonth)

    @Test
    fun `constraint1 penalizes same room in same slot`() {
        val penalized = Array(1) {
            createExamDay().apply {
                addLesson(ScheduledLesson("L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL))
                addLesson(ScheduledLesson("L2", "P2", "3", "D2", RoomSize.SMALL, "A1", RoomSize.SMALL))
            }
        }
        val notPenalized = Array(1) {
            createExamDay().apply {
                addLesson(ScheduledLesson("L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL))
                addLesson(ScheduledLesson("L2", "P2", "3", "D2", RoomSize.SMALL, "A2", RoomSize.SMALL))
            }
        }
        assertTrue(
            State(penalized, 1, ConstraintWeights()).fitness < State(notPenalized, 1, ConstraintWeights()).fitness,
            "Same room should reduce fitness"
        )
    }

    @Test
    fun `constraint2 penalizes same department and semester in same slot`() {
        val penalized = Array(1) {
            createExamDay().apply {
                addLesson(ScheduledLesson("L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL))
                addLesson(ScheduledLesson("L2", "P2", "1", "D1", RoomSize.SMALL, "A2", RoomSize.SMALL))
            }
        }
        val notPenalized = Array(1) {
            createExamDay().apply {
                addLesson(ScheduledLesson("L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL))
                addLesson(ScheduledLesson("L2", "P2", "3", "D2", RoomSize.SMALL, "A2", RoomSize.SMALL))
            }
        }
        assertTrue(
            State(penalized, 1, ConstraintWeights()).fitness < State(notPenalized, 1, ConstraintWeights()).fitness,
            "Same department+semester should reduce fitness"
        )
    }

    @Test
    fun `constraint4 penalizes unavailable professor`() {
        val penalized = Array(1) {
            createExamDay(dayFromMonth = "2").apply {
                addLesson(
                    ScheduledLesson(
                        "L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL,
                        professorUnavailableDays = setOf("2", "9")
                    )
                )
            }
        }
        val notPenalized = Array(1) {
            createExamDay(dayFromMonth = "3").apply {
                addLesson(
                    ScheduledLesson(
                        "L2", "P2", "3", "D2", RoomSize.SMALL, "A2", RoomSize.SMALL,
                        professorUnavailableDays = setOf("2", "9")
                    )
                )
            }
        }
        assertTrue(
            State(penalized, 1, ConstraintWeights()).fitness < State(notPenalized, 1, ConstraintWeights()).fitness,
            "Unavailable professor should reduce fitness"
        )
    }

    @Test
    fun `constraint4 no penalty for available professor`() {
        val examDays = Array(1) {
            createExamDay(dayFromMonth = "3").apply {
                addLesson(
                    ScheduledLesson(
                        "L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL,
                        professorUnavailableDays = setOf("2", "9")
                    )
                )
            }
        }

        val state = State(examDays, 1, ConstraintWeights())
        // Professor IS available on day "3" (not in unavailable set). Full constraint base scores.
        assertTrue(state.fitness >= ConstraintWeights().constraint1Base, "Professor available: no c4 penalty")
    }

    @Test
    fun `custom weights affect scoring`() {
        val examDays = Array(1) {
            createExamDay().apply {
                addLesson(ScheduledLesson("L1", "P1", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL))
                addLesson(ScheduledLesson("L2", "P2", "1", "D1", RoomSize.SMALL, "A1", RoomSize.SMALL))
            }
        }

        val defaultFitness = State(examDays, 1, ConstraintWeights()).fitness
        val heavyFitness = State(examDays, 1, ConstraintWeights(constraint1Penalty = 50, constraint2Penalty = 50)).fitness

        assertTrue(heavyFitness < defaultFitness, "Heavier penalties should reduce score more")
    }
}
