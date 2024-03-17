plugins {
    id("com.squareup.wire") version "4.9.1"
}

wire {
    protoLibrary = true

    sourcePath {
        srcDir("src/main/proto")
    }

    sourcePath {
        srcJar("com.google.protobuf:protobuf-java:3.25.3")
        include("google/protobuf/field_mask.proto")
    }

    kotlin {
        rpcRole = "client"
        rpcCallStyle = "suspending"
    }
}
