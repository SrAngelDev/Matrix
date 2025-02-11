plugins {
    kotlin("jvm") version "2.0.21"
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "srangeldev"
version = "BETA 1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //logger
    implementation("org.lighthousegames:logging.1.5.0")
    implementation("ch.qos.logback:logback-classic:1.5.12")

    //mordant
    implementation("com.github.ajalt.mordant:mordant:1.5.12")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "srangeldev.MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    archiveFileName.set("howarts.jar")
}


