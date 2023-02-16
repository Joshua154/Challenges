

plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.5.1"
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "com.laudynetwork.challenges"
version = "latest"
description = "Challenge Plugin for LaudyNetwork"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}


dependencies {
    implementation("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    paperDevBundle("1.19.2-R0.1-SNAPSHOT")

    implementation("com.laudynetwork:networkutils:latest")

    implementation("de.dytanic.cloudnet:cloudnet-driver:3.4.5-RELEASE")
    implementation("de.dytanic.cloudnet:cloudnet-bridge:3.4.5-RELEASE")
}
repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.cloudnetservice.eu/repository/releases/")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven {
        url = uri("https://repo.laudynetwork.com/repository/maven")
        authentication {
            create<BasicAuthentication>("basic")
        }
        credentials {
            username = System.getenv("NEXUS_USER")
            password = System.getenv("NEXUS_PWD")
        }
    }
}