package com.texniti.ergasia

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TypesTest {

    @Test
    fun `DayOfWeek fromIndex maps correctly`() {
        assertEquals(DayOfWeek.MONDAY, DayOfWeek.fromIndex(0))
        assertEquals(DayOfWeek.FRIDAY, DayOfWeek.fromIndex(4))
        assertEquals(DayOfWeek.MONDAY, DayOfWeek.fromIndex(5)) // wraps
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.fromIndex(11))
    }

    @Test
    fun `TimeSlot fromIndex maps correctly`() {
        assertEquals(TimeSlot.SLOT_9_12, TimeSlot.fromIndex(0))
        assertEquals(TimeSlot.SLOT_12_15, TimeSlot.fromIndex(1))
        assertEquals(TimeSlot.SLOT_15_18, TimeSlot.fromIndex(2))
        assertEquals(TimeSlot.SLOT_9_12, TimeSlot.fromIndex(3)) // wraps
    }

    @Test
    fun `RoomSize fromLabel rounds trips correctly`() {
        assertEquals(RoomSize.SMALL, RoomSize.fromLabel("small"))
        assertEquals(RoomSize.NORMAL, RoomSize.fromLabel("normal"))
        assertEquals(RoomSize.LARGE, RoomSize.fromLabel("large"))
    }
}
