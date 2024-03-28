plugins {
    alias(libs.plugins.indraSonatype)
}

allprojects {
    group = "net.megavex"
    version = "0.1.0-SNAPSHOT"
    description = "Suspending Agones client SDK for Kotlin"
}

indraSonatype {
    useAlternateSonatypeOSSHost("s01")
}
