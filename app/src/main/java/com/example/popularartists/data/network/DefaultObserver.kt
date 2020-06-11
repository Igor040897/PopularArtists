package com.example.popularartists.data.network

import androidx.lifecycle.Observer

class DefaultObserver<E : Any?, T : ResultObject<E>> : Observer<T> {
    private var onError: (errorResult: ResultObject.ErrorResult) -> Unit = {}
    private var onSuccess: (successResult: ResultObject.SuccessResult<E>) -> Unit = {}

    @Suppress("UNCHECKED_CAST")
    override fun onChanged(t: T) {
        when (t) {
            is ResultObject.SuccessResult<*> -> onSuccess(t as ResultObject.SuccessResult<E>)
            is ResultObject.ErrorResult -> onError(t)
        }
    }

    fun handleError(
        withDefault: Boolean = true,
        handler: (errorResult: ResultObject.ErrorResult) -> Unit
    ): DefaultObserver<E, T> {
        onError = if (withDefault) {
            val default = onError
            {
                default(it)
                handler(it)
            }
        } else {
            handler
        }
        return this
    }

    fun handleSuccess(
        withDefault: Boolean = true,
        handler: (successResult: ResultObject.SuccessResult<E>) -> Unit
    ): DefaultObserver<E, T> {
        onSuccess = if (withDefault) {
            val default = onSuccess
            {
                default(it)
                handler(it)
            }
        } else {
            handler
        }
        return this
    }
}