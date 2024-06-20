plugins {
    `java-library`
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {

    // Kotlin
    implementation(kotlin("stdlib"))

    // Coroutines
    implementation(libs.cooroutines)

    // Kotlinx serialization
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.json)

    testImplementation(libs.junit)
}