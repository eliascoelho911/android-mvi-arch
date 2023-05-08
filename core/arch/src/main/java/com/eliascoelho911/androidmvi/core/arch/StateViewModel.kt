package com.eliascoelho911.androidmvi.core.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

abstract class StateViewModel<Event, Action, State>(
    private val initialState: State,
) : ViewModel() {
    private val event = MutableSharedFlow<Event>()

    val state: StateFlow<State> = event.toState()

    private val _action = MutableSharedFlow<Action>()
    val action: SharedFlow<Action> get() = _action

    suspend fun dispatch(event: Event) {
        this.event.emit(event)
    }

    suspend fun dispatch(eventFlow: Flow<Event>) {
        event.emitAll(eventFlow)
    }

    protected abstract fun reducer(state: State, event: Event): State

    protected suspend fun sendAction(action: Action) {
        _action.emit(action)
    }

    private fun Flow<Event>.toState(): StateFlow<State> {
        return map { event ->
            reducer(state.value, event)
        }.stateIn(viewModelScope, SharingStarted.Lazily, initialState)
    }
}