package com.texniti.ergasia

enum class RoomSize(val label: String) {
    SMALL("small"),
    NORMAL("normal"),
    LARGE("large");

    companion object {
        fun fromLabel(label: String): RoomSize =
            entries.firstOrNull { it.label == label }
                ?: throw IllegalArgumentException("Unknown room size: $label")
    }
}

enum class DayOfWeek(val label: String) {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday");

    companion object {
        fun fromIndex(index: Int): DayOfWeek = entries[index.mod(entries.size)]
    }
}

enum class TimeSlot(val label: String) {
    SLOT_9_12("9:00-12:00"),
    SLOT_12_15("12:00-15:00"),
    SLOT_15_18("15:00-18:00");

    companion object {
        fun fromIndex(index: Int): TimeSlot = entries[index.mod(entries.size)]
    }
}
