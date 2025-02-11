// Neo.kt
package srangeldev.models

import java.time.LocalDateTime

class Neo(
    id: Long,
    nombre: String,
    localizacion: Localizacion,
    edad: Int,
    createdAt: LocalDateTime,
    var esElegido: Boolean
) : Personaje(id, nombre, localizacion, edad, createdAt) {

    fun actuar(matrix: MutableList<MutableList<Personaje?>>) {
        if ((0..1).random() == 1) {
            esElegido = true
            println("Neo cree que es el elegido.")
            eliminarSmithsAlrededor(matrix)
        } else {
            println("Neo no está seguro de ser el elegido.")
        }
        mover(matrix)
    }

    private fun eliminarSmithsAlrededor(matrix: MutableList<MutableList<Personaje?>>) {
        val (row, col) = obtenerPosicion(matrix)
        for (i in -1..1) {
            for (j in -1..1) {
                val newRow = row + i
                val newCol = col + j
                if (newRow in matrix.indices && newCol in matrix[0].indices) {
                    if (matrix[newRow][newCol] is Smith) {
                        matrix[newRow][newCol] = null
                        println("Neo ha eliminado a un Smith en ($newRow, $newCol).")
                    }
                }
            }
        }
    }

    private fun mover(matrix: MutableList<MutableList<Personaje?>>) {
        val (row, col) = obtenerPosicion(matrix)
        var newRow: Int
        var newCol: Int
        do {
            newRow = (0 until matrix.size).random()
            newCol = (0 until matrix[0].size).random()
        } while (matrix[newRow][newCol] != null)
        matrix[newRow][newCol] = this
        matrix[row][col] = null
        println("Neo se ha movido a (${newRow + 1}, ${newCol + 1}).")
    }

    private fun obtenerPosicion(matrix: MutableList<MutableList<Personaje?>>): Pair<Int, Int> {
        for (row in matrix.indices) {
            for (col in matrix[row].indices) {
                if (matrix[row][col] == this) {
                    return Pair(row, col)
                }
            }
        }
        return Pair(-1, -1)
    }
}

