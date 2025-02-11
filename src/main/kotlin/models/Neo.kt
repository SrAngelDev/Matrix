package srangeldev.models

import java.time.LocalDateTime

class Neo(
    id: Long,
    nombre: String,
    localizacion: Localizacion,
    edad: Int,
    createdAt: LocalDateTime,
    var esElegido: Boolean
): Personaje(id, nombre, localizacion, edad, createdAt) {
    fun generar() {
        TODO("Not yet implemented")
    }

    override fun pedir() {
        TODO("Not yet implemented")
    }

    override fun mostrar() {
        TODO("Not yet implemented")
    }
}