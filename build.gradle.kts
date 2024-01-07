plugins {
    kotlin("jvm") version "1.9.20" apply false
}

allprojects {
    version = "0.1.0-SNAPSHOT"
    group = "net.megavex"
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("com.squareup.wire:wire-grpc-client:4.9.1")
    }

    extensions.getByType(PublishingExtension::class.java).publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
