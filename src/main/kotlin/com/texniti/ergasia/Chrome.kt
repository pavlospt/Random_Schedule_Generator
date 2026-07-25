package com.texniti.ergasia

import com.models.Instructors
import com.models.Rooms
import com.models.Slots

class Chrome(
    private val map: CustomMap,
    private val rooms: List<Rooms>,
    private val instructors: List<Instructors>,
    private val examDayTemplates: List<ExamDay>
) : Comparable<Chrome> {

    var score: Int = 0
        private set
    val size: Int get() = map.size

    constructor(
        rooms: List<Rooms>,
        courses: List<com.models.Courses>,
        slots: List<Slots>,
        instructors: List<Instructors>
    ) : this(
        map = CustomMap(rooms, courses, slots),
        rooms = rooms.toList(),
        instructors = instructors.toList(),
        examDayTemplates = fillExamDayArray(slots.size * 4, slots)
    ) {
        calculateScore()
    }

    companion object {
        fun fromParent(
            map: CustomMap,
            rooms: List<Rooms>,
            instructors: List<Instructors>,
            slots: List<Slots>
        ): Chrome = Chrome(
            map = CustomMap(map),
            rooms = rooms.toList(),
            instructors = instructors.toList(),
            examDayTemplates = fillExamDayArray(slots.size * 4, slots)
        ).also { it.calculateScore() }
    }

    private fun calculateScore() {
        val state = State(examDayTemplates, map, rooms, instructors)
        score = state.fitness
    }

    fun getMap(): CustomMap = map
    fun getMapKey(index: Int): LessonObject = map.getKey(index)
    fun getMapValue(index: Int): DayTimeSlot = map.getValue(index)

    fun mutate(dayTimeSlots: List<DayTimeSlot>) {
        val randomIndex = (0 until map.size).random()
        map.setValue(randomIndex, DayTimeSlot(dayTimeSlots.random()))
        calculateScore()
    }

    fun print() = map.print()

    override fun toString(): String = "Chrome [score=$score]"

    override fun compareTo(other: Chrome): Int = this.score.compareTo(other.score)
}
