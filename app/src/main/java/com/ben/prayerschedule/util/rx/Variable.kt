package com.ben.prayerschedule.util.rx

import io.reactivex.subjects.BehaviorSubject

/**
 * Created by clarksandholtz on 4/2/18.
 */
class Variable<T>(private val defaultValue: T) {
    var value: T = defaultValue
        set(value) {
            field = value
            observable.onNext(value)
        }
    val observable = BehaviorSubject.createDefault(value)
}