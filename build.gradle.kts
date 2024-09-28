plugins {
    id("java")
}

group = "ru.decalium.std"
version = "1.0"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}