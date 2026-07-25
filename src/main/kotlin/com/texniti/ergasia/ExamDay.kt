package com.texniti.ergasia

data class ExamDay(
    val time: TimeSlot,
    val day: DayOfWeek,
    var dayFromMonth: String = "",
    val lessons: MutableList<ScheduledLesson> = mutableListOf()
) {
    val lessonCount: Int get() = lessons.size
    val rooms: List<String> get() = lessons.map { it.room }
    val departments: List<String> get() = lessons.map { it.department }
    val semesters: List<String> get() = lessons.map { it.semester }

    fun getLesson(index: Int): ScheduledLesson = lessons[index]
    fun addLesson(lesson: ScheduledLesson) = lessons.add(lesson)

    fun isProfessorUnavailable(index: Int, dayOfMonth: String): Boolean =
        lessons[index].professorUnavailableDays.contains(dayOfMonth)

    fun copyTemplate(): ExamDay = ExamDay(time, day, dayFromMonth)

    override fun toString(): String = "${day.label} ${time.label}"
}
