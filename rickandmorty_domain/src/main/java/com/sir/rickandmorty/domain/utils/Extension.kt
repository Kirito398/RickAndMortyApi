package com.sir.rickandmorty.domain.utils

fun String.ifEmptyNull(): String? = this.ifEmpty { null }