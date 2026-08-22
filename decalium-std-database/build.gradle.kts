plugins {
    id("decalium.conventions")
}

dependencies {
    compileOnlyApi("com.zaxxer:HikariCP:5.0.1") {

    }
    compileOnlyApi("org.jdbi:jdbi3-core:3.53.0") {
    }
    compileOnlyApi("org.flywaydb:flyway-core:10.12.0") {
        exclude("com.google.code.gson", "gson")
    }
    compileOnlyApi("org.flywaydb:flyway-mysql:10.12.0") {
        exclude("com.google.code.gson", "gson")
    }

    compileOnly("org.spongepowered:configurate-core:4.1.2")
}