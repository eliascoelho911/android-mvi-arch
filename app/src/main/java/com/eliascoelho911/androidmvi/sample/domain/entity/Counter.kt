package com.eliascoelho911.androidmvi.sample.domain.entity

data class Counter(val value: Int) {
    fun increment() = copy(value = value + 1)
    fun decrement() = copy(value = value - 1)
}