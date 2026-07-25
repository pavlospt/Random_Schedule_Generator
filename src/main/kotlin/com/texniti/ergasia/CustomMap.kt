package com.texniti.ergasia

import com.models.Courses
import com.models.Rooms
import com.models.Slots
import kotlin.random.Random

class CustomMap {
    private val lessonObjectArray: MutableList<LessonObject>
    private val dayTimeSlotArray: MutableList<DayTimeSlot>

    val size: Int get() = lessonObjectArray.size

    constructor(rooms: List<Rooms>, courses: List<Courses>, slots: List<Slots>) {
        lessonObjectArray = mutableListOf()
        dayTimeSlotArray = mutableListOf()

        val lessons = fillLessons(courses, rooms)
        val dayTimeSlots = createDayTimeSlots(slots)

        for (i in lessons.indices) {
            lessonObjectArray.add(lessons[i])
            dayTimeSlotArray.add(dayTimeSlots[Random.nextInt(dayTimeSlots.size)])
        }
    }

    constructor(map: CustomMap) {
        lessonObjectArray = mutableListOf()
        dayTimeSlotArray = mutableListOf()

        for (i in 0 until map.size) {
            lessonObjectArray.add(map.getKey(i))
            dayTimeSlotArray.add(map.getValue(i))
        }
    }

    constructor() {
        lessonObjectArray = mutableListOf()
        dayTimeSlotArray = mutableListOf()
    }

    fun addKey(obj: LessonObject) = lessonObjectArray.add(obj)
    fun addValue(obj: DayTimeSlot) = dayTimeSlotArray.add(obj)
    fun setKey(index: Int, obj: LessonObject) { lessonObjectArray[index] = obj }
    fun setValue(index: Int, obj: DayTimeSlot) { dayTimeSlotArray[index] = obj }
    fun getKey(index: Int): LessonObject = lessonObjectArray[index]
    fun getValue(index: Int): DayTimeSlot = dayTimeSlotArray[index]

    fun print() {
        for (i in lessonObjectArray.indices) {
            println("${lessonObjectArray[i]} --> ${dayTimeSlotArray[i]}")
        }
    }

    override fun toString(): String =
        "CustomMap [mLessonObjectArray=$lessonObjectArray, mDayTimeSlot=$dayTimeSlotArray]"
}
