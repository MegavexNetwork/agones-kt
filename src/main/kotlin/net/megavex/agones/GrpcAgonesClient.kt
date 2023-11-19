package net.megavex.agones

import agones.dev.sdk.Empty
import agones.dev.sdk.KeyValue
import agones.dev.sdk.SDKClient
import kotlinx.coroutines.coroutineScope
import kotlin.time.Duration
import agones.dev.sdk.Duration as AgonesDuration

internal class GrpcAgonesClient(private val client: SDKClient) : AgonesClient {
    private companion object {
        val EMPTY = Empty()
    }

    override suspend fun ready() {
        client.Ready().execute(EMPTY)
    }

    override suspend fun allocate() {
        client.Allocate().execute(EMPTY)
    }

    override suspend fun shutdown() {
        client.Shutdown().execute(EMPTY)
    }

    override suspend fun health(): Unit = coroutineScope {
        // TODO: why is the stream randomly closing?
        val (send, _) = client.Health().executeIn(this)
        send.send(EMPTY)
        send.close()
    }

    override suspend fun setLabel(key: String, value: String) {
        client.SetLabel().execute(KeyValue(key, value))
    }

    override suspend fun setAnnotation(key: String, value: String) {
        client.SetAnnotation().execute(KeyValue(key, value))
    }

    override suspend fun reserve(duration: Duration) {
        client.Reserve().execute(AgonesDuration(seconds = duration.inWholeSeconds))
    }
}