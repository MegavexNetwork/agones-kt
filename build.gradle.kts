plugins {
    kotlin("jvm") version "1.9.20" apply false
    id("net.kyori.indra") version "3.1.3"
}

allprojects {
    version = "0.1.0-SNAPSHOT"
    group = "net.megavex"
}

subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "net.kyori.indra")

    repositories {
        mavenCentral()
    }

    dependencies {
        "implementation"("com.squareup.wire:wire-grpc-client:4.9.7")
    }

    indra {
        github("MegavexNetwork", "agones-kt") {
            ci(true)
        }
        mitLicense()

        javaVersions {
            minimumToolchain(17)
        }
    }
}
