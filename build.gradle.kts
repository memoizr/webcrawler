import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Look you can write DSLs in Kotlin. It's actually a joy to do that.
plugins {
    kotlin("jvm") version "1.4.10"
    application
}

group = "net.1account"
version = "1.0"

repositories {
    maven("https://jitpack.io")
    mavenCentral()
}
tasks.jar {
    manifest {
        attributes("Main-Class" to "App")
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
dependencies {
    implementation("org.jsoup:jsoup:1.11.2")
    testImplementation(kotlin("test-junit"))
}
tasks.withType<JavaCompile>() {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "MainKt"
}