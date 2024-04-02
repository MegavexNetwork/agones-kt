plugins {
    id("net.megavex.agones.base-conventions")
}

dependencies {
    implementation(project(":agones-generated"))
}

kotlin {
    explicitApi()
}
