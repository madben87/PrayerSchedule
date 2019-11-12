package com.ben.prayerschedule.util.rx

import androidx.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class ReadOnlyRxObservableField<T>(
    private val source: Observable<T>,
    disposables: CompositeDisposable,
    default: T? = null
) : ObservableField<T>() {

    val rx: Observable<T> = default?.let { Observable.concat(Observable.just(default), source) }
        ?: source

    init {
        disposables.add(rx.doOnNext { super.set(it) }.subscribe())
    }

    fun or(default: T): T = get() ?: default

    @Deprecated("This class is ReadOnly!", ReplaceWith("not call"))
    override fun set(value: T) {
        throw UnsupportedOperationException()
    }
}