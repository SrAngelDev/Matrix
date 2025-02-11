package srangeldev.models

data class Localizacion(
    val latitud: Int,
    val longitud: Int,
    val ciudad: String = listOf("Nueva York", "Pekín", "Roma", "Paris", "Londres", "Caracuel").random()
)
