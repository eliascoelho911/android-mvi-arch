package com.eliascoelho911.androidmvi.sample.presentation.counter

import com.eliascoelho911.androidmvi.core.arch.StateViewModel
import com.eliascoelho911.androidmvi.sample.domain.repository.CounterRepository

class CounterViewModel(
    repository: CounterRepository
) : StateViewModel<CounterEvent, CounterSideEffect, CounterState>(
    reducer = CounterReducer(
        initialState = CounterState(),
        repository = repository
    )
) {
    init {
        dispatch(CounterEvent.Started)
    }
}