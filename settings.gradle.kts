plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "decalium-std"
include("decalium-std-paper")
include("decalium-std-database")
include("decalium-std-adventure")
include("decalium-std-config")
include("decalium-std-java")
include("decalium-std-commands")
