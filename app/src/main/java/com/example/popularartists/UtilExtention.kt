package com.example.popularartists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateView(@LayoutRes layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}

//todo refactoring or remove
//fun <T> AppCompatActivity.observe(liveData: LiveData<T>, callback: (T) -> Unit) {
//    liveData.observe(this, Observer {
//        callback.invoke(it)
//    })
//}
//
//fun <T> LifecycleOwner.observeCommand(data: LiveData<T>, action: (T) -> Unit) {
//    data.observe(this, Observer(action))
//}
//
//fun <T> LifecycleOwner.observe(data: LiveData<T>, action: (T) -> Unit) {
//    observeCommand(data, action)
//}
//
//fun <T> Fragment.observeOnView(data: LiveData<T>, action: (T) -> Unit) {
//    data.observe(viewLifecycleOwner, Observer(action))
//}
//
//@MainThread
//fun <T : Any> MutableLiveData<T>.call() {
//    value = null
//}