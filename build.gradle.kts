plugins {
    kotlin("jvm") version "1.9.20"
    id("com.squareup.wire") version "4.9.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.wire:wire-grpc-client:4.9.1")
}

wire {
    kotlin {
        rpcRole = "client"
        rpcCallStyle = "suspending"
    }
}
