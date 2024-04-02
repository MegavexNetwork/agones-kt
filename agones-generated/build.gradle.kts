plugins {
    id("megavex.base-conventions")
    alias(libs.plugins.wire)
}

wire {
    protoLibrary = true

    sourcePath {
        srcDir("src/main/proto")
    }

    sourcePath {
        srcJar(libs.protobuf)
        include("google/protobuf/field_mask.proto")
    }

    kotlin {
        rpcRole = "client"
        rpcCallStyle = "suspending"
    }
}
