package srangeldev.factories

import org.lighthousegames.logging.logging
import srangeldev.models.Genericos
import java.util.LinkedList
import java.util.Queue
import java.time.LocalDateTime


class GenericosFactory {
    companion object {
        val logger = logging()
        var nextId = 1L
        fun getNewId(): Long {
            return nextId++
        }

        fun random(): Queue<Genericos> {
            logger.debug { "Generando Personajes Genericos en Factory" }
            val numEnemigos = 200
            val colaEnemigos: Queue<Genericos> = LinkedList()
            val nombres = listOf("Pepe", "Juan", "Ana", "Sonia", "Pedro", "Chiquito", "Elena")

            repeat(numEnemigos) {
                colaEnemigos.add(
                    Genericos(
                        id = getNewId(),
                        nombre = nombres.random(),
                        edad = (0..65).random(),
                        createdAt = LocalDateTime.now(),
                        probMorir = (0..100).random()
                    )
                )
            }
            return colaEnemigos
        }
    }
}






