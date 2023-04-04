

plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.5.4"
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

    paperDevBundle("1.19.3-R0.1-SNAPSHOT")

    implementation("com.laudynetwork:networkutils:latest")

    api("eu.thesimplecloud.simplecloud:simplecloud-api:2.4.1")
}
repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.thesimplecloud.eu/artifactory/list/gradle-release-local/")
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