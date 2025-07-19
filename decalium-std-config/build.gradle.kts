plugins {
    id("decalium.conventions")
}

dependencies {
    api("org.spongepowered:configurate-yaml:4.1.2")
    api(project(":decalium-std-java"))
    compileOnly("net.kyori:adventure-api:4.17.0")
    compileOnly("net.kyori:adventure-text-minimessage:4.17.0")


}