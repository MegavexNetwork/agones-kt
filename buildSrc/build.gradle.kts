plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.buildKotlin)
    implementation(libs.buildIndra)
    implementation(libs.buildDetekt)
    implementation(libs.buildNmcp)
    compileOnly(files(libs::class.java.protectionDomain.codeSource.location))
}
