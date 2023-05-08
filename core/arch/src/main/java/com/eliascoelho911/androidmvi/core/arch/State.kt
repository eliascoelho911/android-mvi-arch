package com.eliascoelho911.androidmvi.core.arch

typealias SyncState = Any

interface State {
    val syncState: SyncState
}