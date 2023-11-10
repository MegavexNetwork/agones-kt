plugins {
    `maven-publish`
    kotlin("jvm") version "1.9.20"
    id("com.squareup.wire") version "4.9.1"
}

version = "0.1.0-SNAPSHOT"
group = "net.megavex"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.wire:wire-grpc-client:4.9.1")
}

wire {
    protoLibrary = true

    kotlin {
        rpcRole = "client"
        rpcCallStyle = "suspending"
    }
}

kotlin {
    explicitApi()
}

publishing.publications.create<MavenPublication>("maven") {
    from(components["java"])
}
