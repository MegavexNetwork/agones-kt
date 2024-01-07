package net.megavex.agones

import agones.dev.sdk.SDKClient
import com.squareup.wire.GrpcClient
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import java.io.IOException
import kotlin.time.Duration

public fun AgonesClient(httpClient: OkHttpClient): AgonesClient {
    val port = System.getenv("AGONES_SDK_GRPC_PORT")?.toIntOrNull() ?: 9357
    return AgonesClient(httpClient, "http://127.0.0.1:$port")
}

public fun AgonesClient(httpClient: OkHttpClient, url: String): AgonesClient {
    val grpcClient = GrpcClient.Builder()
        .client(httpClient)
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
    public suspend fun health(pings: Flow<Unit>)

    // TODO: GetGameServer, WatchGameServer

    @Throws(IOException::class)
    public suspend fun setLabel(key: String, value: String)

    @Throws(IOException::class)
    public suspend fun setAnnotation(key: String, value: String)

    @Throws(IOException::class)
    public suspend fun reserve(duration: Duration)
}