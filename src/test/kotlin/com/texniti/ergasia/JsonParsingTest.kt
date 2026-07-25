package com.texniti.ergasia

import com.models.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class JsonParsingTest {

    private fun testResource(path: String): File =
        File("src/test/resources/$path")

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `parses courses json`() {
        val wrapper = testResource("courses.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Courses>>>(stream)
        }
        val courses = wrapper["Courses"]!!
        assertEquals(70, courses.size)
        assertEquals("Programmatismos se Java", courses[0].courseName)
        assertEquals("PL0101", courses[0].courseCode)
        assertEquals("Kuriazis", courses[0].instructor)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `parses instructors json`() {
        val wrapper = testResource("instructors.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Instructors>>>(stream)
        }
        val instructors = wrapper["Instructors"]!!
        assertTrue(instructors.isNotEmpty())
        assertEquals("Kuriazis", instructors[0].instructor)
        assertEquals("2", instructors[0].nonAvailable)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `parses rooms json`() {
        val wrapper = testResource("rooms.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Rooms>>>(stream)
        }
        val rooms = wrapper["Rooms"]!!
        assertEquals(6, rooms.size)
        assertEquals("A1", rooms[0].roomName)
        assertEquals("small", rooms[0].roomSpace)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `parses slots json`() {
        val wrapper = testResource("slots.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Slots>>>(stream)
        }
        val slots = wrapper["Days"]!!
        assertEquals(5, slots.size)
        assertEquals("Monday", slots[0].dayName)
        assertEquals("2,9,16,23", slots[0].calendar)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun `parses departments json`() {
        val wrapper = testResource("departments.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Departments>>>(stream)
        }
        val departments = wrapper["departments"]!!
        assertEquals(7, departments.size)
        assertEquals("Plhroforikh", departments[0].departmentName)
    }
}
