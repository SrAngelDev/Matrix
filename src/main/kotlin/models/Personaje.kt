package srangeldev.models

import java.time.LocalDateTime

const val NEW_ID = 0L

abstract class Personaje(
    var id: Long = NEW_ID,
    val nombre: String,
    val localizacion: Localizacion = Localizacion(0, 0),
    val ciudad: String,
    val edad: Int,
    val createdAt: LocalDateTime,
) {
    abstract fun generar()
    abstract fun pedir()
    abstract fun mostrar()
}