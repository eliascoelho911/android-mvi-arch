package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.core.arch.State
import com.eliascoelho911.androidmvi.sample.domain.entity.Counter

data class CounterState(
    val counter: Counter = Counter(0),
    override val viewState: CounterSyncState = CounterSyncState.Loading
) : State {
    fun loading() = copy(viewState = CounterSyncState.Loading)

    fun content(counter: Counter) = copy(counter = counter, viewState = CounterSyncState.Content)

    fun error(message: String) = copy(viewState = CounterSyncState.Error(message))
}

sealed interface CounterSyncState {
    object Loading : CounterSyncState

    object Content : CounterSyncState

    data class Error(val message: String) : CounterSyncState
}