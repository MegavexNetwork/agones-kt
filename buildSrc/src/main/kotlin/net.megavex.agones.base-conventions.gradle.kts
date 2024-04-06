plugins {
    kotlin("jvm")
    id("net.kyori.indra")
    id("net.kyori.indra.publishing")
    id("io.gitlab.arturbosch.detekt")
    id("com.gradleup.nmcp")
}

val libs = extensions.getByType(org.gradle.accessors.dm.LibrariesForLibs::class)

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.wireGrpcClient)
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
            url = "https://github.com/MegavexNetwork/agones-kt"

            developers {
                developer {
                    id = "vytskalt"
                    name = "vytskalt"
                }
            }
        }
    }
}

nmcp {
    val centralUsername = findProperty("centralUsername") as String?
    val centralPassword = findProperty("centralPassword") as String?

    if (centralUsername != null && centralPassword != null) {
        publishAllPublications {
            username = centralUsername
            password = centralPassword
            publicationType = "USER_MANAGED"
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$projectDir/detekt.yml")
}
