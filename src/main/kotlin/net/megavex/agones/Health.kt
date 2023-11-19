package net.megavex.agones

import kotlinx.coroutines.delay
import java.io.IOException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Throws(IOException::class)
public suspend fun runHealthPingTask(client: AgonesClient, period: Duration = 5.seconds) {
    while (true) {
        client.health()
        delay(period)
    }
}
