package srangeldev.models

import kotlin.system.exitProcess

/**
 * Configuración para la simulación.
 *
 * @property mapSize Tamaño del mapa.
 */
class Configuration private constructor(
    val mapSize: Int = 5,
) {
    companion object {
        fun fromArgs(args2: Array<String>): Configuration {
            if (args2.size != 1) {
                showErrorMessage()
            }

            val mapSize = args2[0].toIntOrNull() ?: -1

            if (mapSize != 5) {
                showErrorMessage()
            }

            return Configuration(mapSize)
        }

        private fun showErrorMessage() {
            println("Argumentos inválidos")
            println("Uso: java -jar matrix.jar <mapSize> ")
            println("Ejemplo: java -jar matrix.jar 5")
            exitProcess(-1)
        }
    }
}
