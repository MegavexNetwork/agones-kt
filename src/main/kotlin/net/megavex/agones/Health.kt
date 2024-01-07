package net.megavex.agones

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

public fun healthFlow(period: Duration = 5.seconds): Flow<Unit> = flow {
    while (true) {
        emit(Unit)
        delay(period)
    }
}
