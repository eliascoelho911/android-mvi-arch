package com.eliascoelho911.androidmvi.core.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<EVENT, SIDE_EFFECT, STATE : State>(
    private val reducer: Reducer<EVENT, SIDE_EFFECT, STATE>
) : ViewModel() {

    val state: StateFlow<STATE> get() = reducer.state

    val sideEffect: SharedFlow<SIDE_EFFECT> get() = reducer.sideEffect

    fun dispatch(event: EVENT) {
        viewModelScope.launch {
            reducer.dispatch(event)
        }
    }
}