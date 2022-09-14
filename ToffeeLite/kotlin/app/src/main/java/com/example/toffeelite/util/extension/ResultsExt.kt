package com.example.toffeelite.util.extension

import com.example.toffeelite.data.model.response.Results

fun <T> Results<T>.onSuccess(onResult: Results.Success<T>.() -> Unit): Results<T> {
    if (this is Results.Success) onResult(this)
    return this
}

fun <T> Results<T>.onFailure(onResult: Results.Failure<*>.() -> Unit): Results<T> {
    if (this is Results.Failure<*>) onResult(this)
    return this
}

fun <T> Results<T>.onException(onResult: Results.Exception<*>.() -> Unit): Results<T> {
    if (this is Results.Exception<*>) onResult(this)
    return this
}