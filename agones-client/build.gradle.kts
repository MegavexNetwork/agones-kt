plugins {
    id("megavex.base-conventions")
}

dependencies {
    implementation(project(":agones-generated"))
}

kotlin {
    explicitApi()
}
