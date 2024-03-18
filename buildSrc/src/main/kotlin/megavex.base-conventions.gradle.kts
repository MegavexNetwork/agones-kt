plugins {
    kotlin("jvm")
    id("net.kyori.indra")
    id("net.kyori.indra.publishing")
}

repositories {
    mavenCentral()
}

dependencies {
    "implementation"("com.squareup.wire:wire-grpc-client:4.9.8")
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
