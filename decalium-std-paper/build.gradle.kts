plugins {
    id("decalium.conventions")
}

repositories {
    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
}

dependencies {
    api("ru.decalium:action:0.0.1")
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("org.spongepowered:configurate-core:4.1.2")
}