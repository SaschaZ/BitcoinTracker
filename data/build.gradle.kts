
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    namespace = "dev.zieger.bitcointracker.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation(project(":domain"))

    // Kotlin
    implementation(kotlin("stdlib"))

    // Coroutines
    implementation(libs.cooroutines)

    // Ktor
    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.contentNegotiation)
    implementation(libs.ktor.json)
    implementation(libs.slf4j)

    // Kotlinx serialization
    implementation(libs.kotlin.serialization)
    implementation(libs.kotlin.serialization.json)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)


    // Test
    testImplementation(libs.junit)
}