package srangeldev.controllers

import org.lighthousegames.logging.logging
import srangeldev.factories.GenericosFactory
import srangeldev.models.*
import java.time.LocalDateTime
import java.util.*

class Simulacion(
    private val mapSize: Int
) {
    private val matrix = MutableList(mapSize) { MutableList<Personaje?>(mapSize) { null } }
    private val genericosQueue: Queue<Genericos> = GenericosFactory.random()
    private val smithsDeposito = mutableListOf<Smith>()
    private var neo: Neo? = null
    private var smith: Smith? = null
    private val logger = logging()

    private fun inicializarMatrix() {
        logger.debug { "Inicializando matrix" }
        neo = Neo(
            id = GenericosFactory.getNewId(),
            nombre = "Neo",
            localizacion = Localizacion(0, 0),
            edad = 30,
            createdAt = LocalDateTime.now(),
            esElegido = false
        )
        colocarPersonaje(neo!!)

        smith = Smith(
            id = GenericosFactory.getNewId(),
            nombre = "Smith",
            localizacion = Localizacion(0, 0),
            edad = 40,
            createdAt = LocalDateTime.now(),
            probInfectar = 50
        )
        colocarPersonaje(smith!!)

        repeat(mapSize * mapSize - 2) {
            if (genericosQueue.isNotEmpty()) {
                colocarPersonaje(genericosQueue.poll())
            }
        }
    }

    private fun colocarPersonaje(personaje: Personaje) {
        logger.debug { "Colocando personaje $personaje" }
        var placed = false
        while (!placed) {
            val row = (0 until mapSize).random()
            val col = (0 until mapSize).random()
            if (matrix[row][col] == null) {
                matrix[row][col] = personaje
                placed = true
            }
        }
    }

    fun iniciarSimulacion() {
        logger.debug { "Iniciando simulacion" }
        println("Iniciando simulación de Matrix...")

        inicializarMatrix()

        var time = 0
        while (time < 300 && !todosSonSmithONull(matrix)) {
            println("Tiempo: $time segundos")
            evaluarMuertes()

            if (time % 2 == 0) {
                smith?.actuar(matrix)
            }
            if (time % 5 == 0) {
                neo?.actuar(matrix, smithsDeposito)
                imprimirMatrix()
            }
            if (time % 10 == 0) {
                agregarPersonajes()
            }

            Thread.sleep(1000)
            time++
        }

        imprimirInformeFinal()
    }

    private fun evaluarMuertes() {
        for (row in 0 until mapSize) {
            for (col in 0 until mapSize) {
                val personaje = matrix[row][col]
                if (personaje is Genericos) {
                    if (personaje.probMorir < 30) {
                        matrix[row][col] = null
                        println("${personaje.nombre} ha muerto.")
                    } else {
                        personaje.probMorir -= 10
                    }
                }
            }
        }
    }

    private fun agregarPersonajes() {
        repeat(5) {
            if (genericosQueue.isNotEmpty()) {
                colocarPersonaje(genericosQueue.poll())
            }
        }
    }

    private fun imprimirMatrix() {
        println("Estado de Matrix:")
        for (row in matrix) {
            for (personaje in row) {
                when (personaje) {
                    is Neo -> print("| N | ")
                    is Smith -> print("| S | ")
                    is Genericos -> print("| P | ")
                    else -> print("|   | ")
                }
            }
            println()
        }
    }

    private fun imprimirInformeFinal() {
        println("--- Informe Final ---")
        println("Neo: ${neo?.localizacion}")
        println("Smiths eliminados: ${smithsDeposito.size}")
        println("Personajes generados: ${genericosQueue.size}")
    }

    private fun todosSonSmithONull(matrix: MutableList<MutableList<Personaje?>>): Boolean {
        for (row in matrix) {
            for (personaje in row) {
                if (personaje != null && personaje !is Smith) {
                    return false
                }
            }
        }
        return true
    }
}
