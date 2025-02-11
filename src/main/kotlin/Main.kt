package srangeldev

import srangeldev.factories.GenericosFactory
import srangeldev.models.Configuration

fun main(args: Array<String>) {
    val config = Configuration.fromArgs(args)
    val genericos = GenericosFactory.random()
    genericos.forEach { println(it) }
}