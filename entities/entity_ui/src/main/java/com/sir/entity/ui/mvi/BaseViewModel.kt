package com.sir.entity.ui.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<E, S>(
    initialState: S,
) : ViewModel(), EventHandler<E> {

    protected val mutableViewState = MutableStateFlow(initialState)
    val viewState: StateFlow<S> = mutableViewState.asStateFlow()
}
