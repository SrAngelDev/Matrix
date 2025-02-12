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

    fun actuar(matrix: MutableList<MutableList<Personaje?>>, smithsDeposito: MutableList<Smith>) {
        if ((0..1).random() == 1) {
            esElegido = true
            println("Neo cree que es el elegido.")
            eliminarSmithsAlrededor(matrix, smithsDeposito)
        } else {
            println("Neo no está seguro de ser el elegido.")
        }
        mover(matrix)
    }

    private fun eliminarSmithsAlrededor(matrix: MutableList<MutableList<Personaje?>>, smithsDeposito: MutableList<Smith>) {
        val row = obtenerPosicionFila(matrix)
        val col = obtenerPosicionColumna(matrix)
        for (i in -1..1) {
            for (j in -1..1) {
                val newRow = row + i
                val newCol = col + j
                if (newRow in matrix.indices && newCol in matrix[0].indices) {
                    if (matrix[newRow][newCol] is Smith) {
                        val smith = matrix[newRow][newCol] as Smith
                        matrix[newRow][newCol] = null
                        smithsDeposito.add(smith)
                        println("Neo ha eliminado a un Smith en ($newRow, $newCol) y lo ha añadido al depósito.")
                    }
                }
            }
        }
    }

    private fun mover(matrix: MutableList<MutableList<Personaje?>>) {
        val row = obtenerPosicionFila(matrix)
        val col = obtenerPosicionColumna(matrix)
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
