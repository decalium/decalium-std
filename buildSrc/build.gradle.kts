import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    id("com.gradleup.shadow") version "8.3.2"
}

repositories {
    mavenCentral()
    gradlePluginPortal()

}

dependencies {
    implementation("com.gradleup.shadow:shadow-gradle-plugin:8.3.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "17"
}

