package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.core.arch.StateViewModel

class CounterViewModel(
    reducer: CounterReducer
) : StateViewModel<CounterEvent, CounterSideEffect, CounterState>(reducer) {
    init {
        dispatch(CounterEvent.Started)
    }
}