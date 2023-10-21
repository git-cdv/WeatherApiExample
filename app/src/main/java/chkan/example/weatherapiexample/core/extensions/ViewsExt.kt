package chkan.example.weatherapiexample.core.extensions

import android.view.View

fun View.onClick(listener: ((View) -> Unit)?) {
    this.setOnClickListener { listener?.invoke(it) }
}