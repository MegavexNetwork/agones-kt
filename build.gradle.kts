plugins {
    alias(libs.plugins.indraSonatype)
}

allprojects {
    group = "net.megavex"
    version = "0.1.0"
    description = "Suspending Agones client SDK for Kotlin"
}

indraSonatype {
    useAlternateSonatypeOSSHost("s01")
}
