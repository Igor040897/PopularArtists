package com.example.popularartists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.popularartists.data.models.Artist
import com.example.popularartists.data.network.DefaultObserver
import com.example.popularartists.data.network.ResultObject

const val NOT_RESULT_THROWABLE = "Not Result"

fun ViewGroup.inflateView(@LayoutRes layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}

fun <E : Any?, T : ResultObject<E>> LifecycleOwner.observe(
    data: LiveData<T>,
    successAction: (E) -> Unit,
    errorAction: ((ResultObject.ErrorResult) -> Unit)? = null
) {
    data.observe(this, DefaultObserver<E, T>()
        .handleSuccess {
            it.getResult()?.apply {
                successAction.invoke(this)
            } ?: errorAction?.invoke(ResultObject.ErrorResult(Throwable(NOT_RESULT_THROWABLE)))
        }
        .handleError {
            errorAction?.invoke(it)
        })
}