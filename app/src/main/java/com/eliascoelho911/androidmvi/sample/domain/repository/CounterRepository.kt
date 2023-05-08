package com.eliascoelho911.androidmvi.sample.domain.repository

import com.eliascoelho911.androidmvi.sample.domain.entity.Counter

interface CounterRepository {
    suspend fun get(): Result<Counter>

    suspend fun increment(): Result<Counter>

    suspend fun decrement(): Result<Counter>
}