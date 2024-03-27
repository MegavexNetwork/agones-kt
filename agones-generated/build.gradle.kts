plugins {
    id("megavex.base-conventions")
    id("com.squareup.wire") version "4.9.8"
}

wire {
    protoLibrary = true

    sourcePath {
        srcDir("src/main/proto")
    }

    sourcePath {
        srcJar("com.google.protobuf:protobuf-java:4.26.1")
        include("google/protobuf/field_mask.proto")
    }

    kotlin {
        rpcRole = "client"
        rpcCallStyle = "suspending"
    }
}
