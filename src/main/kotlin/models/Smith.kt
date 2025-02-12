// Smith.kt
package srangeldev.models

import java.time.LocalDateTime

class Smith(
    id: Long,
    nombre: String,
    localizacion: Localizacion,
    edad: Int,
    createdAt: LocalDateTime,
    var probInfectar: Int
) : Personaje(id, nombre, localizacion, edad, createdAt) {

    fun actuar(matrix: MutableList<MutableList<Personaje?>>) {
        val row = obtenerPosicionFila(matrix)
        val col = obtenerPosicionColumna(matrix)
        val capacidadInfectar = (1..probInfectar).random()
        println("Smith intenta infectar con capacidad $capacidadInfectar.")
        infectarAlrededor(matrix, row, col, capacidadInfectar)
    }

    private fun infectarAlrededor(matrix: MutableList<MutableList<Personaje?>>, row: Int, col: Int, capacidad: Int) {
        var infectados = 0
        for (i in -1..1) {
            for (j in -1..1) {
                val newRow = row + i
                val newCol = col + j
                if (newRow in matrix.indices && newCol in matrix[0].indices) {
                    val personaje = matrix[newRow][newCol]
                    if (personaje is Genericos && infectados < capacidad) {
                        matrix[newRow][newCol] = this // Smith reemplaza al personaje
                        infectados++
                        println("Smith ha infectado a ${personaje.nombre} en ($newRow, $newCol).")
                    }
                }
            }
        }
    }

    private fun obtenerPosicionFila(matrix: MutableList<MutableList<Personaje?>>): Int {
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == this) {
                    return row
                }
            }
        }
        return -1
    }

    private fun obtenerPosicionColumna(matrix: MutableList<MutableList<Personaje?>>): Int {
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == this) {
                    return col
                }
            }
        }
        return -1
    }
}