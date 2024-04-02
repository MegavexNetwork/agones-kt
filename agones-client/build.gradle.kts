plugins {
    id("megavex.base-conventions")
}

dependencies {
    implementation(project(":agones-generated"))
}

nmcp {
    publishAllPublications {}
}

kotlin {
    explicitApi()
}
