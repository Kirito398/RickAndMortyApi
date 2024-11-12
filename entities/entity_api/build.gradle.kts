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
    implementation(libs.kotlinx.coroutines.core)

    api(libs.retrofit2.main)
    api(libs.retrofit2.converter.json)
    implementation(libs.logging.interceptor)
}
