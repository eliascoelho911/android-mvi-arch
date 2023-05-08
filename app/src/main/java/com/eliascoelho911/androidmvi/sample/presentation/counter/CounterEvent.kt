package com.eliascoelho911.androidmvi.sample.presentation.counter

sealed interface CounterEvent {
    object Started : CounterEvent
    object Increment : CounterEvent
    object Decrement : CounterEvent
}