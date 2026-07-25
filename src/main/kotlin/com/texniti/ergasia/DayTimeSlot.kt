package com.texniti.ergasia

data class DayTimeSlot(
    var day: String = "",
    var time: String = "",
    var dayFromMonth: String = ""
) {
    constructor(other: DayTimeSlot) : this(other.day, other.time, other.dayFromMonth)

    override fun toString(): String = "$day -- $dayFromMonth -- $time"
}
