package com.eliascoelho911.androidmvi.sample.presentation.counter

sealed interface CounterSideEffect {
    data class ShowToast(val message: String) : CounterSideEffect
}