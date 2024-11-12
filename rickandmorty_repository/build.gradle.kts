plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "17" // Set Kotlin target JVM to 17
}

dependencies {
    //implementation(project(":rickandmorty_api"))
    api(project(":rickandmorty_domain"))

    implementation(libs.kotlinx.coroutines.core)
}