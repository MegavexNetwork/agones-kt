plugins {
    alias(libs.plugins.nmcp)
}

allprojects {
    group = "net.megavex"
    version = "0.1.0"
    description = "Suspending Agones client SDK for Kotlin"

    apply(plugin = rootProject.libs.plugins.nmcp.get().pluginId)
}

nmcp {
    publishAggregation {
        project(":agones-client")
        project(":agones-generated")

        username = findProperty("centralUsername") as String
        password = findProperty("centralPassword") as String
        publicationType = "USER_MANAGED"
    }
}
