package com.texniti.ergasia

import com.models.Courses
import com.models.Instructors
import com.models.Rooms
import com.models.Slots
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class Genetic(
    courses: List<Courses>,
    private val rooms: List<Rooms>,
    private val instructors: List<Instructors>,
    private val slots: List<Slots>
) {
    private var population: MutableList<Chrome> = mutableListOf()
    private val courses = courses.toList()

    companion object {
        private const val TOURNAMENT_SIZE = 3
    }

    fun reproduce(x: Chrome, y: Chrome): Chrome {
        val childGenes = CustomMap()
        val intersectionPoint = (0 until x.size).random()

        for (i in 0 until intersectionPoint) {
            childGenes.addKey(x.getMapKey(i))
            childGenes.addValue(x.getMapValue(i))
        }
        for (i in intersectionPoint until x.size) {
            childGenes.addKey(y.getMapKey(i))
            childGenes.addValue(y.getMapValue(i))
        }
        return Chrome.fromParent(childGenes, rooms, instructors, slots)
    }

    private fun tournamentSelect(): Chrome {
        val candidates = (1..TOURNAMENT_SIZE).map { population.random() }
        return candidates.maxBy { it.score }
    }

    fun geneticAlgorithm(
        populationSize: Int,
        mutationProbability: Double,
        minimumFitness: Int,
        maximumSteps: Int,
        parallel: Boolean = false
    ): Chrome {
        initializePopulation(populationSize)

        for (step in 0 until maximumSteps) {
            val newPopulation = if (parallel) {
                runBlocking { generatePopulationParallel(populationSize, mutationProbability) }
            } else {
                generatePopulationSequential(populationSize, mutationProbability)
            }

            population = newPopulation.toMutableList()
            population.sortDescending()

            if (population[0].score >= minimumFitness) {
                println("Finished after $step steps...")
                return population[0]
            }
        }

        println("Finished after $maximumSteps steps...")
        return population[0]
    }

    private fun generatePopulationSequential(
        populationSize: Int,
        mutationProbability: Double
    ): List<Chrome> = List(populationSize) {
        generateChild(mutationProbability)
    }

    private suspend fun generatePopulationParallel(
        populationSize: Int,
        mutationProbability: Double
    ): List<Chrome> = coroutineScope {
        (0 until populationSize).map {
            async { generateChild(mutationProbability) }
        }.awaitAll()
    }

    private fun generateChild(mutationProbability: Double): Chrome {
        var x = tournamentSelect()
        var y: Chrome
        do {
            y = tournamentSelect()
        } while (y === x)

        val child = reproduce(x, y)
        if (Random.nextDouble() < mutationProbability) {
            child.mutate(createDayTimeSlots(slots))
        }
        return child
    }

    private fun initializePopulation(populationSize: Int) {
        population = MutableList(populationSize) {
            Chrome(rooms, courses, slots, instructors)
        }
    }
}
