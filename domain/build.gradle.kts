plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //Coroutine
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}