package com.texniti.ergasia

import com.models.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

object JsonLoader {
    @PublishedApi
    internal val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    inline fun <reified T> loadList(filePath: String): Result<List<T>> = runCatching {
        File(filePath).inputStream().use { stream ->
            @Suppress("UNCHECKED_CAST")
            val wrapper = json.decodeFromStream<Map<String, List<T>>>(stream)
            wrapper.values.firstOrNull() ?: emptyList()
        }
    }
}

fun main() {
    val basePath = "${System.getProperty("user.dir")}/src/main/resources/"

    val coursesResult = JsonLoader.loadList<Courses>("$basePath/courses.json")
    val instructorsResult = JsonLoader.loadList<Instructors>("$basePath/instructors.json")
    val roomsResult = JsonLoader.loadList<Rooms>("$basePath/rooms.json")
    val slotsResult = JsonLoader.loadList<Slots>("$basePath/slots.json")

    listOf(coursesResult, instructorsResult, roomsResult, slotsResult).forEach { result ->
        result.onFailure { error ->
            System.err.println("Failed to load data: ${error.message}")
            return
        }
    }

    val courses = coursesResult.getOrThrow()
    val instructors = instructorsResult.getOrThrow()
    val rooms = roomsResult.getOrThrow()
    val slots = slotsResult.getOrThrow()

    val genetic = Genetic(courses, rooms, instructors, slots)
    val bestChrome = genetic.geneticAlgorithm(
        populationSize = 100,
        mutationProbability = 0.01,
        minimumFitness = 300,
        maximumSteps = 1000,
        parallel = true
    )

    println("Score--> ${bestChrome.score}")

    val templates = fillExamDayArray(slots.size * 4, slots)
    val schedule = State(templates, bestChrome.getMap(), rooms, instructors).toString()

    runCatching {
        File("schedule.txt").writeText(schedule)
    }.onSuccess {
        println("schedule.txt successfully created.")
    }.onFailure { error ->
        System.err.println("Failed to write schedule: ${error.message}")
    }
}
