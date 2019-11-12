package com.ben.prayerschedule.util.rx

import androidx.databinding.ObservableField
import io.reactivex.Observable


class RxObservableField<T> : ObservableField<T> {

    @Transient
    private var observable: Observable<T>? = null
    @Transient
    private var valueFilter: ((T) -> Boolean)? = null
    val rx: Observable<T>
        get() = observable ?: ObservableUtils.toObservable(this).also { observable = it }

    constructor() : super()

    constructor(default: T) : super(default)

    fun or(default: T): T = get() ?: default

    override fun set(value: T) {
        valueFilter?.let {
            if (it.invoke(value)) super.set(value)
        } ?: super.set(value)
    }

    fun setValueFilter(valueFilter: ((T) -> Boolean)): RxObservableField<T> {
        return also { it.valueFilter = valueFilter }
    }

}