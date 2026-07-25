package com.texniti.ergasia

import com.models.Courses
import com.models.Instructors
import com.models.Rooms
import kotlin.random.Random

class State(
    private val examDays: Array<ExamDay>,
    private val weights: ConstraintWeights = ConstraintWeights()
) : Comparable<State> {

    var fitness: Int = 0
        private set
    var coursesArraySize: Int = 0

    /** Random schedule: assigns courses to random slots */
    constructor(
        coursesList: List<Courses>,
        roomsList: List<Rooms>,
        examDayTemplates: List<ExamDay>,
        instructorsList: List<Instructors>,
        weights: ConstraintWeights = ConstraintWeights()
    ) : this(
        examDays = examDayTemplates.map { it.copyTemplate() }.toTypedArray(),
        weights = weights
    ) {
        coursesArraySize = coursesList.size
        val random = Random(System.currentTimeMillis())

        for (course in coursesList) {
            val randomLesson = random.nextInt(examDays.size)
            val randomRoom = random.nextInt(roomsList.size)
            val instructor = instructorsList.firstOrNull { it.instructor == course.instructor }
            val unavailableDays = instructor?.nonAvailable?.split(",")?.toSet() ?: emptySet()

            examDays[randomLesson].addLesson(
                ScheduledLesson(
                    lesson = course.courseName,
                    professor = course.instructor,
                    semester = course.semester,
                    department = course.department,
                    requiredRoomSize = RoomSize.fromLabel(course.requiredClassSize),
                    room = roomsList[randomRoom].roomName,
                    roomSize = RoomSize.fromLabel(roomsList[randomRoom].roomSpace),
                    professorUnavailableDays = unavailableDays
                )
            )
        }
        calculateHeuristic()
    }

    /** Copy constructor from an existing ExamDay array */
    constructor(
        source: Array<ExamDay>,
        size: Int,
        weights: ConstraintWeights = ConstraintWeights()
    ) : this(
        examDays = source.copyOf(),
        weights = weights
    ) {
        coursesArraySize = size
        calculateHeuristic()
    }

    /** Convert from Chromosome (CustomMap) representation to ExamDays */
    constructor(
        templates: List<ExamDay>,
        map: CustomMap,
        roomsList: List<Rooms>,
        instructorsList: List<Instructors>,
        weights: ConstraintWeights = ConstraintWeights()
    ) : this(
        examDays = templates.map { it.copyTemplate() }.toTypedArray(),
        weights = weights
    ) {
        val random = Random(System.currentTimeMillis())

        for (j in 0 until map.size) {
            val key = map.getKey(j)
            val value = map.getValue(j)

            for (i in examDays.indices) {
                if (value.dayFromMonth == examDays[i].dayFromMonth &&
                    value.time == examDays[i].time.label
                ) {
                    val randomRoom = random.nextInt(roomsList.size)
                    val instructor = instructorsList.firstOrNull { it.instructor == key.professor }
                    val unavailableDays = instructor?.nonAvailable?.split(",")?.toSet() ?: emptySet()

                    examDays[i].addLesson(
                        ScheduledLesson(
                            lesson = key.lessonName,
                            professor = key.professor,
                            semester = key.semester,
                            department = key.department,
                            requiredRoomSize = RoomSize.fromLabel(key.requiredRoomSize),
                            room = roomsList[randomRoom].roomName,
                            roomSize = RoomSize.fromLabel(roomsList[randomRoom].roomSpace),
                            professorUnavailableDays = unavailableDays
                        )
                    )
                }
            }
        }
        calculateHeuristic()
    }

    fun getSlots(): Array<ExamDay> = examDays

    private fun calculateHeuristic() {
        fitness = 0
        fitness += constraint1()
        fitness += constraint2()
        fitness += constraint3()
        fitness += constraint4()
        fitness += constraint5()
        fitness += constraint6()
        fitness += constraint7()
        fitness += constraint8()
    }

    private fun constraint1(): Int {
        var counter = weights.constraint1Base
        for (day in examDays) {
            if (day.lessonCount > 1) {
                for (j in 0 until day.lessonCount) {
                    for (k in j + 1 until day.lessonCount) {
                        if (day.lessons[j].room == day.lessons[k].room) {
                            counter -= weights.constraint1Penalty
                        }
                    }
                }
            }
        }
        return counter
    }

    private fun constraint2(): Int {
        var counter = weights.constraint2Base
        for (day in examDays) {
            if (day.lessonCount > 1) {
                for (j in 0 until day.lessonCount) {
                    for (k in j + 1 until day.lessonCount) {
                        if (day.lessons[j].semester == day.lessons[k].semester &&
                            day.lessons[j].department == day.lessons[k].department
                        ) {
                            counter -= weights.constraint2Penalty
                        }
                    }
                }
            }
        }
        return counter
    }

    private fun constraint3(): Int {
        var counter = weights.constraint3Base
        for (day in examDays) {
            if (day.lessonCount > 1) {
                for (lesson in day.lessons) {
                    if (lesson.roomSize == lesson.requiredRoomSize) {
                        counter -= weights.constraint3Penalty
                    }
                }
            }
        }
        return counter
    }

    private fun constraint4(): Int {
        var counter = weights.constraint4Base
        for (day in examDays) {
            for (lesson in day.lessons) {
                if (lesson.professorUnavailableDays.contains(day.dayFromMonth)) {
                    counter -= weights.constraint4Penalty
                }
            }
        }
        return counter
    }

    private fun constraint5(): Int {
        var counter = weights.constraint5Base
        for (i in examDays.indices step 3) {
            if (i + 2 >= examDays.size) continue
            val combined = (0..2).map { offset ->
                examDays[i + offset].lessons.map { it.department + it.semester }
            }
            for (x in 0 until combined.size) {
                for (y in x + 1 until combined.size) {
                    for (item in combined[x]) {
                        if (item in combined[y]) counter -= weights.constraint5Penalty
                    }
                }
            }
        }
        return counter
    }

    private fun constraint6(): Int {
        var counter = weights.constraint6Base
        for (i in examDays.indices step 3) {
            if (i + 5 >= examDays.size) continue
            if (examDays[i].day == DayOfWeek.FRIDAY) continue
            val now = (i until i + 3).flatMap { k ->
                examDays[k].lessons.map { "${it.department} ${it.semester}" }
            }
            val next = (i + 3 until i + 6).flatMap { k ->
                examDays[k].lessons.map { "${it.department} ${it.semester}" }
            }
            val overlap = now.count { it in next }
            if (overlap > 0) counter -= overlap * weights.constraint6Penalty
        }
        return counter
    }

    private fun constraint7(): Int {
        var counter = weights.constraint7Base
        for (i in examDays.indices step 3) {
            if (i + 2 >= examDays.size) continue
            val departments = (i until i + 3).flatMap { j -> examDays[j].lessons.map { it.department } }
            val doubles = countDoubles(departments)
            if (doubles > 0) counter -= doubles * weights.constraint7Penalty
        }
        return counter
    }

    private fun constraint8(): Int {
        var counter = weights.constraint8Base
        for (i in examDays.indices step 3) {
            if (i + 5 >= examDays.size) continue
            if (examDays[i].day == DayOfWeek.FRIDAY) continue
            val now = (i until i + 3).flatMap { k -> examDays[k].lessons.map { it.department } }
            val next = (i + 3 until i + 6).flatMap { k -> examDays[k].lessons.map { it.department } }
            val overlap = now.count { it in next }
            if (overlap > 0) counter -= overlap * weights.constraint8Penalty
        }
        return counter
    }

    private fun countDoubles(list: List<String>): Int {
        var count = 0
        for (i in list.indices) {
            for (j in i + 1 until list.size) {
                if (list[i] == list[j]) count++
            }
        }
        return count
    }

    override fun toString(): String = buildString {
        for (day in examDays) {
            if (day.lessonCount > 0) {
                appendLine("${day.day.label} ${day.dayFromMonth}/12/2013 ,Time : ${day.time.label}")
                for (lesson in day.lessons) {
                    val status = if (lesson.professorUnavailableDays.contains(day.dayFromMonth))
                        "(Not Available)" else "(Available)"
                    appendLine(
                        "\tLesson : ${lesson.lesson}, Department : ${lesson.department}, " +
                            "Semester : ${lesson.semester}, Room : ${lesson.room}, " +
                            "Professor : ${lesson.professor}$status"
                    )
                }
                appendLine()
            }
        }
    }

    override fun compareTo(other: State): Int = this.fitness.compareTo(other.fitness)
}
