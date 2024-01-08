package net.megavex.agones

import agones.dev.sdk.Empty
import agones.dev.sdk.KeyValue
import agones.dev.sdk.SDKClient
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
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

    override suspend fun health(pings: Flow<Unit>) {
        val call = client.Health()
        try {
            coroutineScope {
                val (tx, _) = client.Health().executeIn(this)
                pings.collect {
                    tx.send(EMPTY)
                }
            }
        } finally {
            call.cancel()
        }
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