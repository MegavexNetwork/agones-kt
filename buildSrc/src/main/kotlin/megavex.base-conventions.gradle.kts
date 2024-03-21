plugins {
    kotlin("jvm")
    id("net.kyori.indra")
    id("net.kyori.indra.publishing")
    id("io.gitlab.arturbosch.detekt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.wire:wire-grpc-client:4.9.8")
}

indra {
    github("MegavexNetwork", "agones-kt") {
        ci(true)
    }
    mitLicense()

    javaVersions {
        minimumToolchain(17)
    }

    configurePublications {
        pom {
            developers {
                developer {
                    id = "vytskalt"
                    name = "vytskalt"
                }
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$projectDir/detekt.yml")
}
