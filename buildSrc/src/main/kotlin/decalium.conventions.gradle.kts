plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.decalium.ru/releases/")
}

dependencies {
    compileOnly("com.google.code.gson:gson:2.10.1")
    compileOnly("com.google.guava:guava:25.1-jre")
    compileOnly("org.slf4j:slf4j-api:2.0.12")
    compileOnly("org.jetbrains:annotations:24.0.0")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
        options.compilerArgs.add("-parameters")
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
}


publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "ru.decalium"
            artifactId = project.name
            version = version

            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "decalium" //  optional target repository name
            url = uri("https://repo.decalium.ru/releases/")
            credentials {
                username = System.getenv("REPOSILITE_USER")
                password = System.getenv("REPOSILITE_TOKEN")
            }
        }
    }
}





