plugins {
    id("com.squareup.wire") version "4.9.1"
}

wire {
    protoLibrary = true

    kotlin {
        rpcRole = "client"
        rpcCallStyle = "suspending"
    }
}
