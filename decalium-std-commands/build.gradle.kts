plugins {
    id("decalium.conventions")
}

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-snapshots"
        mavenContent {
            snapshotsOnly()
        }
    }
}

dependencies {
    api("org.incendo:cloud-core:2.0.0-beta.8") {
        exclude("org.checkerframework", "checker-qual")
        exclude("org.apiguardian", "apiguardian-api")
    }
    api("org.incendo:cloud-annotations:2.0.0-rc.2")
    api("org.incendo:cloud-minecraft-extras:2.0.0-beta.8")

    compileOnly("org.spongepowered:configurate-core:4.1.2")
    compileOnly("ru.decalium:action:0.0.1")
}