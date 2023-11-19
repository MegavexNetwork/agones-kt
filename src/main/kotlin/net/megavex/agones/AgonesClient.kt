package net.megavex.agones

import agones.dev.sdk.SDKClient
import com.squareup.wire.GrpcClient
import okhttp3.OkHttpClient
import okhttp3.Protocol
import java.io.IOException
import kotlin.time.Duration

public fun AgonesClient(): AgonesClient {
    val port = System.getenv("AGONES_SDK_GRPC_PORT")?.toIntOrNull() ?: 9357
    return AgonesClient("http://127.0.0.1:$port")
}

public fun AgonesClient(url: String): AgonesClient {
    val grpcClient = GrpcClient.Builder()
        .client(OkHttpClient.Builder().protocols(listOf(Protocol.H2_PRIOR_KNOWLEDGE)).build())
        .baseUrl(url)
        .minMessageToCompress(Long.MAX_VALUE)
        .build()

    return GrpcAgonesClient(grpcClient.create(SDKClient::class))
}

public interface AgonesClient {
    @Throws(IOException::class)
    public suspend fun ready()

    @Throws(IOException::class)
    public suspend fun allocate()

    @Throws(IOException::class)
    public suspend fun shutdown()

    @Throws(IOException::class)
    public suspend fun health()

    // TODO: GetGameServer, WatchGameServer

    @Throws(IOException::class)
    public suspend fun setLabel(key: String, value: String)

    @Throws(IOException::class)
    public suspend fun setAnnotation(key: String, value: String)

    @Throws(IOException::class)
    public suspend fun reserve(duration: Duration)
}