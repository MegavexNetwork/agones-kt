package net.megavex.agones

import agones.dev.sdk.Empty
import agones.dev.sdk.KeyValue
import agones.dev.sdk.alpha.CounterUpdateRequest
import agones.dev.sdk.alpha.GetCounterRequest
import agones.dev.sdk.alpha.UpdateCounterRequest
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration
import agones.dev.sdk.Duration as AgonesDuration
import agones.dev.sdk.SDKClient as StableClient
import agones.dev.sdk.alpha.SDKClient as AlphaClient

internal class GrpcAgonesClient(private val stableClient: StableClient, private val alphaClient: AlphaClient) :
    AgonesClient {
    private companion object {
        val EMPTY = Empty()
    }

    override suspend fun ready() {
        stableClient.Ready().execute(EMPTY)
    }

    override suspend fun allocate() {
        stableClient.Allocate().execute(EMPTY)
    }

    override suspend fun shutdown() {
        stableClient.Shutdown().execute(EMPTY)
    }

    override suspend fun health(pings: Flow<Unit>) {
        val call = stableClient.Health()
        try {
            coroutineScope {
                val (tx, _) = stableClient.Health().executeIn(this)
                pings.collect {
                    tx.send(EMPTY)
                }
            }
        } finally {
            call.cancel()
        }
    }

    override suspend fun getGameServer(): GameServer {
        val gs = stableClient.GetGameServer().execute(EMPTY)
        val objectMeta = gs.object_meta ?: error("Object meta not set")
        return GameServer(
            ObjectMeta(objectMeta.name)
        )
    }

    override suspend fun setLabel(key: String, value: String) {
        stableClient.SetLabel().execute(KeyValue(key, value))
    }

    override suspend fun setAnnotation(key: String, value: String) {
        stableClient.SetAnnotation().execute(KeyValue(key, value))
    }

    override suspend fun reserve(duration: Duration) {
        stableClient.Reserve().execute(AgonesDuration(seconds = duration.inWholeSeconds))
    }

    override suspend fun getCounter(name: String): Counter {
        val counter = alphaClient.GetCounter().execute(GetCounterRequest(name))
        return Counter(
            counter.name,
            counter.count,
            counter.capacity
        )
    }

    override suspend fun updateCounter(name: String, count: Long?, capacity: Long?, countDiff: Long): Counter {
        val request = CounterUpdateRequest(name, count, capacity, countDiff)
        val counter = alphaClient.UpdateCounter().execute(UpdateCounterRequest(request))
        return Counter(
            counter.name,
            counter.count,
            counter.capacity
        )
    }
}
