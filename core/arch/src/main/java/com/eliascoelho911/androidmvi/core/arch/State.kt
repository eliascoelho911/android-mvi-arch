package com.eliascoelho911.androidmvi.core.arch

typealias ViewState = Any

interface State {
    val viewState: ViewState
}