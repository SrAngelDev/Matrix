package srangeldev.models

import srangeldev.factories.GenericosFactory
import java.time.LocalDateTime
import java.util.Queue

class Genericos(
    id: Long,
    nombre: String,
    localizacion: Localizacion = Localizacion(0,0),
    edad: Int,
    createdAt: LocalDateTime,
    var probMorir: Int
): Personaje(id, nombre, localizacion, edad, createdAt) {

    override fun toString(): String {
        return "Generico(id=$id, nombre=$nombre, localizacion=$localizacion, edad=$edad, createdAt=$createdAt, probMorir=$probMorir)"
    }
}