plugins {
    id("decalium.conventions")
}

dependencies {
    api("com.zaxxer:HikariCP:5.0.1") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    api("org.jdbi:jdbi3-core:3.37.1") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    api("org.flywaydb:flyway-core:10.12.0") {
        exclude("com.google.code.gson", "gson")
    }
    api("org.flywaydb:flyway-mysql:10.12.0") {
        exclude("com.google.code.gson", "gson")
    }

    compileOnly("org.spongepowered:configurate-core:4.1.2")
}