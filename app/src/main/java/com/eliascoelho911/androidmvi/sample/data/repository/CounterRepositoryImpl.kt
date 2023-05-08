package com.eliascoelho911.androidmvi.sample.data.repository

import com.eliascoelho911.androidmvi.sample.domain.entity.Counter
import com.eliascoelho911.androidmvi.sample.domain.repository.CounterRepository
import kotlinx.coroutines.delay

private const val DELAY_TIME = 1000L

class CounterRepositoryImpl: CounterRepository {
    private var counter = Counter(0)

    override suspend fun get(): Result<Counter> {
        delay(DELAY_TIME)
        return Result.success(counter)
    }

    override suspend fun increment(): Result<Counter> {
        delay(DELAY_TIME)
        counter = counter.increment()
        return Result.success(counter)
    }

    override suspend fun decrement(): Result<Counter> {
        delay(DELAY_TIME)
        return Result.failure(RuntimeException("Error"))
    }
}