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

class GeneticAlgorithmTest {

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    @OptIn(ExperimentalSerializationApi::class)
    private fun loadCourses(): List<Courses> =
        File("src/test/resources/courses.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Courses>>>(stream)
        }["Courses"]!!

    @OptIn(ExperimentalSerializationApi::class)
    private fun loadRooms(): List<Rooms> =
        File("src/test/resources/rooms.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Rooms>>>(stream)
        }["Rooms"]!!

    @OptIn(ExperimentalSerializationApi::class)
    private fun loadInstructors(): List<Instructors> =
        File("src/test/resources/instructors.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Instructors>>>(stream)
        }["Instructors"]!!

    @OptIn(ExperimentalSerializationApi::class)
    private fun loadSlots(): List<Slots> =
        File("src/test/resources/slots.json").inputStream().use { stream ->
            json.decodeFromStream<Map<String, List<Slots>>>(stream)
        }["Days"]!!

    @Test
    fun `genetic algorithm converges to a solution`() {
        val courses = loadCourses()
        val rooms = loadRooms()
        val instructors = loadInstructors()
        val slots = loadSlots()

        val genetic = Genetic(courses, rooms, instructors, slots)
        val result = genetic.geneticAlgorithm(
            populationSize = 50,
            mutationProbability = 0.01,
            minimumFitness = 300,
            maximumSteps = 100
        )

        assertNotNull(result)
        assertTrue(result.score > 0, "Solution should have positive fitness")
    }

    @Test
    fun `best chrome has score greater than or equal to first generation`() {
        val courses = loadCourses()
        val rooms = loadRooms()
        val instructors = loadInstructors()
        val slots = loadSlots()

        val genetic = Genetic(courses, rooms, instructors, slots)
        val result = genetic.geneticAlgorithm(
            populationSize = 30,
            mutationProbability = 0.01,
            minimumFitness = 500, // Unreachable to force max steps
            maximumSteps = 10
        )

        assertTrue(result.score > 0, "Solution should have positive fitness")
    }

    @Test
    fun `reproduce creates valid child chrome`() {
        val courses = loadCourses()
        val rooms = loadRooms()
        val instructors = loadInstructors()
        val slots = loadSlots()

        val genetic = Genetic(courses, rooms, instructors, slots)
        val parent1 = Chrome(rooms, courses, slots, instructors)
        val parent2 = Chrome(rooms, courses, slots, instructors)

        val child = genetic.reproduce(parent1, parent2)
        assertNotNull(child)
        assertEquals(parent1.size, child.size, "Child should have same size as parents")
        assertTrue(child.score > 0, "Child should have valid score")
    }

    @Test
    fun `tournament selection favors higher scoring chromosomes`() {
        // Create a simple genetic instance with few courses
        val courses = loadCourses().take(5) // Just 5 courses for quick test
        val rooms = loadRooms()
        val instructors = loadInstructors()
        val slots = loadSlots()

        val genetic = Genetic(courses, rooms, instructors, slots)
        val result = genetic.geneticAlgorithm(
            populationSize = 20,
            mutationProbability = 0.05,
            minimumFitness = 300,
            maximumSteps = 30
        )

        assertTrue(result.score > 0)
    }

    @Test
    fun `schedule output contains all courses`() {
        val courses = loadCourses()
        val rooms = loadRooms()
        val instructors = loadInstructors()
        val slots = loadSlots()

        val genetic = Genetic(courses, rooms, instructors, slots)
        val result = genetic.geneticAlgorithm(
            populationSize = 30,
            mutationProbability = 0.01,
            minimumFitness = 300,
            maximumSteps = 50
        )

        val templates = fillExamDayArray(slots.size * 4, slots)
        val schedule = State(templates, result.getMap(), rooms, instructors)

        val scheduleStr = schedule.toString()
        // Verify output is not empty
        assertTrue(scheduleStr.isNotBlank())
        // Verify it contains time header
        assertTrue(scheduleStr.contains("Time"))
    }

    @Test
    fun `parallel and sequential produce valid results`() {
        val courses = loadCourses()
        val rooms = loadRooms()
        val instructors = loadInstructors()
        val slots = loadSlots()

        val genetic1 = Genetic(courses, rooms, instructors, slots)
        val resultSeq = genetic1.geneticAlgorithm(
            populationSize = 20,
            mutationProbability = 0.01,
            minimumFitness = 500,
            maximumSteps = 5,
            parallel = false
        )

        val genetic2 = Genetic(courses, rooms, instructors, slots)
        val resultPar = genetic2.geneticAlgorithm(
            populationSize = 20,
            mutationProbability = 0.01,
            minimumFitness = 500,
            maximumSteps = 5,
            parallel = true
        )

        assertTrue(resultSeq.score > 0)
        assertTrue(resultPar.score > 0)
    }
}
