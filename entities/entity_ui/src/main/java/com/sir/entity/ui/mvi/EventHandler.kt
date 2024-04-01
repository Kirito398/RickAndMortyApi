package com.sir.entity.ui.mvi

interface EventHandler<E> {
    fun obtainEvent(event: E)
}
