package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.core.arch.Reducer
import com.eliascoelho911.androidmvi.sample.domain.repository.CounterRepository

class CounterReducer(
    private val repository: CounterRepository
) : Reducer<CounterEvent, CounterSideEffect, CounterState>(CounterState()) {
    override suspend fun dispatch(event: CounterEvent) {
        when (event) {
            CounterEvent.Started -> onStarted()
            CounterEvent.Increment -> onIncrement()
            CounterEvent.Decrement -> onDecrement()
        }
    }

    private suspend fun onStarted() {
        on {
            reduce { it.loading() }

            repository.get().onSuccess { counter ->
                reduce { it.content(counter) }
            }.onFailure { throwable ->
                reduce { it.error(throwable.message.orEmpty()) }
            }
        }
    }

    private suspend fun onIncrement() {
        on(guard = { it is CounterSyncState.Content }) {
            sendSideEffect(CounterSideEffect.ShowToast("Incrementing..."))

            repository.increment().onSuccess { counter ->
                reduce { it.content(counter) }
            }.onFailure { throwable ->
                reduce { it.error(throwable.message.orEmpty()) }
            }
        }
    }

    private suspend fun onDecrement() {
        on(guard = { it is CounterSyncState.Content }) {
            sendSideEffect(CounterSideEffect.ShowToast("Decrementing..."))

            repository.decrement().onSuccess { counter ->
                reduce { it.content(counter) }
            }.onFailure { throwable ->
                reduce { it.error(throwable.message.orEmpty()) }
            }
        }
    }
}