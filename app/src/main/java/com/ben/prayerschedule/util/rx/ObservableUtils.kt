/**
 * Code from https://github.com/chuross/rx-observablefield
 * Needed to convert an observable field to an observable so we can merge them and create logic based observable fields
 */
package com.ben.prayerschedule.util.rx

import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import io.reactivex.Observable
import io.reactivex.disposables.Disposables

internal object ObservableUtils {

    @JvmStatic
    fun <T> toObservable(field: ObservableField<T>): Observable<T> {
        return Observable.create { emitter ->
            field.get()?.let { emitter.onNext(it) }

            val callback = object : androidx.databinding.Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(
                    sender: androidx.databinding.Observable?,
                    propertyId: Int
                ) {
                    field.get()?.let {
                        emitter.onNext(it)
                    }
                }
            }

            field.addOnPropertyChangedCallback(callback)

            emitter.setDisposable(Disposables.fromAction {
                field.removeOnPropertyChangedCallback(
                    callback
                )
            })
        }
    }

    @JvmStatic
    fun <T> toObservable(list: ObservableList<T>): Observable<List<T>> {
        return Observable.create { emitter ->
            emitter.onNext(list.toList())

            val callback = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
                override fun onItemRangeChanged(
                    sender: ObservableList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    emitter.onNext(list.toList())
                }

                override fun onItemRangeRemoved(
                    sender: ObservableList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    emitter.onNext(list.toList())
                }

                override fun onChanged(sender: ObservableList<T>?) {
                    emitter.onNext(list.toList())
                }

                override fun onItemRangeMoved(
                    sender: ObservableList<T>?,
                    fromPosition: Int,
                    toPosition: Int,
                    itemCount: Int
                ) {
                    emitter.onNext(list.toList())
                }

                override fun onItemRangeInserted(
                    sender: ObservableList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    emitter.onNext(list.toList())
                }
            }

            list.addOnListChangedCallback(callback)

            emitter.setDisposable(Disposables.fromAction { list.removeOnListChangedCallback(callback) })
        }
    }
}