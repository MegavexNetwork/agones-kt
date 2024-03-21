package net.megavex.agones

import com.squareup.wire.GrpcClient
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import java.io.IOException
import kotlin.time.Duration
import agones.dev.sdk.SDKClient as StableClient
import agones.dev.sdk.alpha.SDKClient as AlphaClient

private const val DEFAULT_AGONES_PORT = 9357

public fun AgonesClient(httpClient: OkHttpClient): AgonesClient {
    val port = System.getenv("AGONES_SDK_GRPC_PORT")?.toIntOrNull() ?: DEFAULT_AGONES_PORT
    return AgonesClient(httpClient, "http://127.0.0.1:$port")
}

public fun AgonesClient(httpClient: OkHttpClient, url: String): AgonesClient {
    val grpcClient = GrpcClient.Builder()
        .client(httpClient)
        .baseUrl(url)
        .minMessageToCompress(Long.MAX_VALUE)
        .build()

    val stableClient = grpcClient.create(StableClient::class)
    val alphaClient = grpcClient.create(AlphaClient::class)
    return GrpcAgonesClient(stableClient, alphaClient)
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

    @Throws(IOException::class)
    public suspend fun getGameServer(): GameServer

    // TODO: WatchGameServer

    @Throws(IOException::class)
    public suspend fun setLabel(key: String, value: String)

    @Throws(IOException::class)
    public suspend fun setAnnotation(key: String, value: String)

    @Throws(IOException::class)
    public suspend fun reserve(duration: Duration)

    // Alpha

    @Throws(IOException::class)
    public suspend fun getCounter(name: String): Counter

    @Throws(IOException::class)
    public suspend fun updateCounter(name: String, count: Long?, capacity: Long?, countDiff: Long = 0): Counter
}
