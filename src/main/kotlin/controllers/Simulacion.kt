package srangeldev.controllers

import org.lighthousegames.logging.logging
import srangeldev.factories.GenericosFactory
import srangeldev.models.*
import java.time.LocalDateTime
import java.util.*
import kotlin.concurrent.schedule

/**
 * Clase que representa la simulación de Matrix.
 *
 * @property mapSize Tamaño del mapa (matriz) donde se desarrolla la simulación.
 */
class Simulacion(
    private val mapSize: Int
) {
    private val matrix = MutableList(mapSize) { MutableList<Personaje?>(mapSize) { null } }
    private val genericosQueue: Queue<Genericos> = GenericosFactory.random()
    private val smithsDeposito = mutableListOf<Smith>()
    private var neo: Neo? = null
    private var smith: Smith? = null
    private val logger = logging()

    /**
     * Inicializa la matriz colocando a Neo, Smith y los personajes genéricos en posiciones aleatorias.
     */
    private fun inicializarMatrix() {
        logger.debug { "Inicializando matrix" }
        // Colocar Neo
        neo = Neo(
            id = GenericosFactory.getNewId(),
            nombre = "Neo",
            localizacion = Localizacion(0, 0),
            edad = 30,
            createdAt = LocalDateTime.now(),
            esElegido = false
        )
        colocarPersonaje(neo!!)

        // Colocar Smith
        smith = Smith(
            id = GenericosFactory.getNewId(),
            nombre = "Smith",
            localizacion = Localizacion(0, 0),
            edad = 40,
            createdAt = LocalDateTime.now(),
            probInfectar = 50
        )
        colocarPersonaje(smith!!)

        // Colocar personajes genéricos, se resta 2 porque ya se ha colocado NEO y SMITH
        repeat(mapSize * mapSize - 2) {
            if (genericosQueue.isNotEmpty()) {
                colocarPersonaje(genericosQueue.poll())
            }
        }
    }

    /**
     * Coloca un personaje en una posición aleatoria de la matriz.
     *
     * @param personaje Personaje a colocar en la matriz.
     */
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

    /**
     * Inicia la simulación de Matrix.
     * La simulación se ejecuta durante un máximo de 300 segundos o hasta que todos los personajes sean Smith o null, que son los que consiguen el control de matrix.
     */
    fun iniciarSimulacion() {
        logger.debug { "Iniciando simulacion" }
        inicializarMatrix()
        println("Iniciando simulación de Matrix...")

        var time = 0
        while (time < 300 && !todosSonSmithONull(matrix)) {
            println("Tiempo: $time segundos")
            evaluarMuertes()

            if (time % 2 == 0) {
                smith?.actuar(matrix)
            }
            if (time % 5 == 0) {
                neo?.actuar(matrix)
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

    /**
     * Evalúa las muertes de los personajes genéricos en la matriz.
     * Si la probabilidad de morir es menor a 30, el personaje muere y es eliminado de la matriz.
     * En caso contrario, se reduce su probabilidad de morir en un 10%.
     */
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

    /**
     * Intenta agregar 5 nuevos personajes genéricos a la matriz si hay espacio disponible.
     */
    private fun agregarPersonajes() {
        repeat(5) {
            if (genericosQueue.isNotEmpty()) {
                colocarPersonaje(genericosQueue.poll())
            }
        }
    }

    /**
     * Imprime el estado actual de la matriz.
     * Muestra "N" para Neo, "S" para Smith, "P" para personajes genéricos y espacios vacíos para celdas vacías.
     */
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

    /**
     * Elimina un Smith de la matriz y lo agrega al depósito de Smiths eliminados.
     *
     * @param row Fila donde se encuentra el Smith.
     * @param col Columna donde se encuentra el Smith.
     */
    fun eliminarSmith(row: Int, col: Int) {
        val smith = matrix[row][col] as? Smith
        if (smith != null) {
            matrix[row][col] = null
            smithsDeposito.add(smith)
            println("Smith eliminado en ($row, $col) y agregado al depósito.")
        }
    }

    /**
     * Imprime el informe final de la simulación.
     * Muestra la localización de Neo, los Smiths eliminados y el número de personajes generados.
     */
    private fun imprimirInformeFinal() {
        println("--- Informe Final ---")
        println("Neo: ${neo?.localizacion}")
        println("Smiths eliminados: ${smithsDeposito.size}")
        println("Personajes generados: ${genericosQueue.size}")
    }

    /**
     * Función para verificar si todos los personajes en la matriz son Smith o null.
     *
     * @param matrix Matriz de personajes.
     * @return `true` si todos los personajes son Smith o null, `false` en caso contrario.
     */
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