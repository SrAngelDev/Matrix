package srangeldev

import srangeldev.controllers.Simulacion
import srangeldev.models.Configuration

fun main(args: Array<String>) {
    val config = Configuration.fromArgs(args)
    val simulacion = Simulacion(config.mapSize)
    simulacion.iniciarSimulacion()
}