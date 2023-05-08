package com.eliascoelho911.androidmvi.core.arch

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer<EVENT, SIDE_EFFECT, STATE : State>(initialState: STATE) {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATE> get() = _state

    private val _sideEffect = MutableSharedFlow<SIDE_EFFECT>()
    val sideEffect: SharedFlow<SIDE_EFFECT> get() = _sideEffect

    abstract suspend fun dispatch(event: EVENT)

    protected suspend fun on(
        guard: (currentState: SyncState) -> Boolean = { true },
        block: suspend () -> Unit
    ) {
        if (guard(_state.value.syncState)) {
            block()
        } else {
            if (BuildConfig.DEBUG) {
                throw IllegalStateException("Guard is false")
            }
        }
    }

    protected suspend fun reduce(state: (currentState: STATE) -> STATE) {
        _state.emit(state(_state.value))
    }

    protected suspend fun sendSideEffect(action: SIDE_EFFECT) {
        _sideEffect.emit(action)
    }
}