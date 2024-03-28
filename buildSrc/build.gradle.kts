plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.buildKotlin)
    implementation(libs.buildIndra)
    implementation(libs.buildDetekt)
}
