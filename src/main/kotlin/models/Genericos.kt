package srangeldev.models

import java.time.LocalDateTime

class Genericos(
    id: Long,
    nombre: String,
    localizacion: Localizacion = Localizacion(0,0),
    ciudad: String,
    edad: Int,
    createdAt: LocalDateTime,
    var probMorir: Int
): Personaje(id, nombre, localizacion, ciudad, edad, createdAt) {
    override fun generar() {
        TODO("Not yet implemented")
    }

    override fun pedir() {
        TODO("Not yet implemented")
    }

    override fun mostrar() {
        TODO("Not yet implemented")
    }
    override fun toString(): String {
        return "Generico(id=$id, nombre=$nombre, localizacion=$localizacion, ciudad=$ciudad, edad=$edad, createdAt=$createdAt, probMorir=$probMorir)"
    }
}