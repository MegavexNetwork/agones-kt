package net.megavex.agones

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.io.IOException
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Throws(IOException::class)
public suspend fun AgonesClient.runHealthUpdater(period: Duration = 5.seconds): Nothing {
    val flow = flow {
        while (true) {
            emit(Unit)
            delay(period)
        }
    }

    health(flow)
    // the flow never ends so the health method can never finish without throwing
    error("unreachable")
}
