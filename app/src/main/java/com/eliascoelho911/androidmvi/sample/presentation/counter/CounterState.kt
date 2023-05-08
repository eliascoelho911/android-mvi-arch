package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.core.arch.State
import com.eliascoelho911.androidmvi.sample.domain.entity.Counter

data class CounterState(
    val counter: Counter = Counter(0),
    override val syncState: CounterSyncState = CounterSyncState.Loading
) : State {
    fun loading() = copy(syncState = CounterSyncState.Loading)

    fun loaded(counter: Counter) = copy(counter = counter, syncState = CounterSyncState.Loaded)

    fun error(message: String) = copy(syncState = CounterSyncState.Error(message))
}

sealed interface CounterSyncState {
    object Loading : CounterSyncState

    object Loaded : CounterSyncState

    data class Error(val message: String) : CounterSyncState
}