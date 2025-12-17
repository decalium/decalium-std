plugins {
    id("java")
}

group = "ru.decalium.std"
version = "1.1.2"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}