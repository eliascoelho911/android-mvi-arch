package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.core.arch.StateViewModel
import com.eliascoelho911.androidmvi.sample.domain.repository.CounterRepository

class CounterViewModel(
    private val repository: CounterRepository
) : StateViewModel<CounterEvent, CounterSideEffect, CounterState>(CounterState()) {

    init {
        dispatch(CounterEvent.Started)
    }

    override suspend fun handleEvent(event: CounterEvent) {
        when (event) {
            CounterEvent.Started -> onStarted()
            CounterEvent.Increment -> onIncrement()
            CounterEvent.Decrement -> onDecrement()
        }
    }

    private suspend fun onStarted() {
        reduce { it.loading() }

        repository.get().onSuccess { counter ->
            reduce { it.loaded(counter) }
        }.onFailure { throwable ->
            reduce { it.error(throwable.message.orEmpty()) }
        }
    }

    private suspend fun onIncrement() {
        on(guard = { it is CounterSyncState.Loaded }) {
            sendSideEffect(CounterSideEffect.ShowToast("Incrementing..."))
            repository.increment().onSuccess { counter ->
                reduce { it.loaded(counter) }
            }.onFailure { throwable ->
                reduce { it.error(throwable.message.orEmpty()) }
            }
        }
    }

    private suspend fun onDecrement() {
        on(guard = { it is CounterSyncState.Loaded }) {
            sendSideEffect(CounterSideEffect.ShowToast("Decrementing..."))
            repository.decrement().onSuccess { counter ->
                reduce { it.loaded(counter) }
            }.onFailure { throwable ->
                reduce { it.error(throwable.message.orEmpty()) }
            }
        }
    }
}