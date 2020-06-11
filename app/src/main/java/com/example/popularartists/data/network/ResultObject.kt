package com.example.popularartists.data.network

sealed class ResultObject<out T : Any?>(protected val resultData: T?) {

    class SuccessResult<T : Any?>(result: T?) : ResultObject<T>(result) {
        fun getResult() = resultData
    }

    class Processing : ResultObject<Unit>(null)

    open class ErrorResult(open val error: Throwable) : ResultObject<Nothing>(null)
}

class ResponseError(val errorCode: Int, override val error: Throwable) :
    ResultObject.ErrorResult(error)


suspend fun <T : Any?> ResultObject<T>.applyToSuccess(
    function: suspend (T) -> Unit
): ResultObject<T> = this.apply {
    when (this) {
        is ResultObject.SuccessResult -> {
            getResult()?.let {
                function(it)
            }
        }
    }
}

suspend fun <T : Any> ResultObject<T>.applyToError(
    function: suspend () -> Unit
): ResultObject<T> = this.apply {
    when (this) {
        is ResultObject.ErrorResult -> {
            function()
        }
    }
}

suspend fun <T : Any> ResultObject<T>.letToSuccess(
    function: suspend (T) -> T
): ResultObject<T> = this.let {
    when (it) {
        is ResultObject.SuccessResult -> {
            it.getResult()?.let { result ->
                ResultObject.SuccessResult(function(result))
            } ?: it
        }
        else -> it
    }
}