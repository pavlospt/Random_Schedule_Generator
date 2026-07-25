# Random Schedule Generator (Kotlin)

Generate random exam schedules for schools and universities using a **genetic algorithm**, with data loaded from JSON files.

Originally a Java project (Artificial Intelligence course, 2013, Athens University of Economics and Business). This is a full Kotlin rewrite with modern idioms and optimizations.

## Quick Start

```bash
git clone https://github.com/pavlospt/Random_Schedule_Generator.git -b kotlin
cd Random_Schedule_Generator
./gradlew run
```

Requires **JDK 21+**.

Output is written to `schedule.txt`.

## Configuration

Edit the JSON files in `src/main/resources/` to match your institution:

| File | Content |
|------|---------|
| `courses.json` | Course code, name, instructor, department, semester, required room size |
| `instructors.json` | Instructor name, code, unavailable days (comma-separated day-of-month) |
| `rooms.json` | Room code, name, size (`small`/`normal`/`large`) |
| `slots.json` | Days of the week with available calendar dates and time ranges |
| `departments.json` | Department names |

### Tuning the genetic algorithm

Edit `Main.kt` to adjust:

```kotlin
genetic.geneticAlgorithm(
    populationSize = 100,
    mutationProbability = 0.01,
    minimumFitness = 300,
    maximumSteps = 1000,
    parallel = true       // Uses kotlinx.coroutines
)
```

### Constraint weights

Customize penalty scores via `ConstraintWeights`:

```kotlin
ConstraintWeights(
    constraint1Base = 100, constraint1Penalty = 1,   // Same room collision
    constraint2Base = 100, constraint2Penalty = 1,   // Same dept+semester collision
    constraint3Base = 70,  constraint3Penalty = 3,   // Room size mismatch
    constraint4Base = 50,  constraint4Penalty = 5,   // Professor unavailable
    constraint5Base = 60,  constraint5Penalty = 4,   // Same day dept+semester
    constraint6Base = 70,  constraint6Penalty = 3,   // Consecutive day dept+semester
    constraint7Base = 50,  constraint7Penalty = 5,   // Same day same department
    constraint8Base = 60,  constraint8Penalty = 4    // Consecutive day same dept
)
```

## Running Tests

```bash
./gradlew test
```

22 tests covering JSON parsing, constraint validation, and genetic algorithm convergence.

## Project Structure

```
src/main/kotlin/com/
├── models/
│   ├── Courses.kt          @Serializable data class
│   ├── Departments.kt
│   ├── Instructors.kt
│   ├── Rooms.kt
│   └── Slots.kt
└── texniti/ergasia/
    ├── Main.kt             Entry point, JSON loading
    ├── Genetic.kt          Genetic algorithm engine (tournament selection)
    ├── Chrome.kt           Chromosome representing a schedule candidate
    ├── State.kt            Schedule state with 8 constraint checks
    ├── ExamDay.kt          Exam day time slot holding scheduled lessons
    ├── ScheduledLesson.kt  Single lesson with all metadata
    ├── CustomMap.kt        Gene representation (LessonObject ↔ DayTimeSlot)
    ├── LessonObject.kt     Lesson data carrier for chromosome
    ├── DayTimeSlot.kt      Day/time slot data carrier
    ├── Types.kt            Enums: RoomSize, DayOfWeek, TimeSlot
    ├── ConstraintWeights.kt Configurable penalty weights
    └── Utilities.kt        Factory functions (fillExamDayArray, etc.)
```

## Key Improvements Over the Java Original

| Change | Impact |
|--------|--------|
| `kotlinx.serialization` data classes | Eliminated 5 Parser classes |
| `ScheduledLesson` data class | Replaced 7 parallel ArrayLists in ExamDay |
| Tournament selection O(k) | Replaced O(n*m) fitnessBounds wheel |
| `kotlinx.coroutines` | Optional parallel population generation |
| `RoomSize`/`DayOfWeek`/`TimeSlot` enums | Type-safe, no string comparison bugs |
| `ConstraintWeights` data class | Tunable penalties at runtime |
| `Result<T>` + `runCatching` | Proper error handling, no swallowed exceptions |
| 22 unit tests | Full coverage of constraints and algorithm |

## Authors

- **Rousas Apostolos** – chesterlos93@gmail.com
- **Tzanoudakis Georgios** – g.tzanoudakhs@gmail.com
- **Tournaris Pavlos-Petros** – p.tournaris@gmail.com
