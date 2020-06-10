package com.example.popularartists.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.popularartists.data.network.ConnectionError
import com.example.popularartists.data.network.ResultObject

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

//    protected val mutableIsProcessing = MutableLiveData<Boolean>()
//
//    val isProcessing: LiveData<Boolean?> = mutableIsProcessing
//
//    class DefaultObserver<E : Any?, T : ResultObject<E>> : Observer<T> {
//        private var onError: (errorResult: ResultObject.ErrorResult) -> Unit = {
//            handleProcessing(false)
//        }
//        private var onSuccess: (successResult: ResultObject.SuccessResult<E>) -> Unit = {
//            handleProcessing(false)
//        }
//        private var onProcessing: (processingResult: ResultObject.Processing) -> Unit = {
//            handleProcessing(true)
//        }
//        private var onConnection: (connectionErrorResult: ConnectionError) -> Unit = {
//            handleProcessing(false)
//        }
//
//        private var shouldHandleProcessing: Boolean = true
//
//        private fun handleProcessing(isProcessing: Boolean) {
//            if (shouldHandleProcessing) {
////                mutableIsProcessing.postValue(isProcessing)
//            }
//        }
//
//        @Suppress("UNCHECKED_CAST")
//        override fun onChanged(t: T) {
//            when (t) {
//                is ResultObject.SuccessResult<*> -> onSuccess(t as ResultObject.SuccessResult<E>)
//                is ResultObject.Processing -> onProcessing(t)
//                is ConnectionError -> onConnection(t)
//                is ResultObject.ErrorResult -> onError(t)
//            }
//        }
//
//        fun handleError(
//            withDefault: Boolean = true,
//            handler: (errorResult: ResultObject.ErrorResult) -> Unit
//        ): DefaultObserver<E, T> {
//            onError = if (withDefault) {
//                val default = onError
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun handleSuccess(
//            withDefault: Boolean = true,
//            handler: (successResult: ResultObject.SuccessResult<E>) -> Unit
//        ): DefaultObserver<E, T> {
//            onSuccess = if (withDefault) {
//                val default = onSuccess
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun handleProcessing(
//            withDefault: Boolean = true,
//            handler: (processingResult: ResultObject.Processing) -> Unit
//        ): DefaultObserver<E, T> {
//            onProcessing = if (withDefault) {
//                val default = onProcessing
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun handleConnection(
//            withDefault: Boolean = true,
//            handler: (connectionError: ConnectionError) -> Unit
//        ): DefaultObserver<E, T> {
//            onConnection = if (withDefault) {
//                val default = onConnection
//                {
//                    default(it)
//                    handler(it)
//                }
//            } else {
//                handler
//            }
//            return this
//        }
//
//        fun dontHandleProcessing(): DefaultObserver<E, T> {
//            shouldHandleProcessing = false
//            return this
//        }
//    }
}