package com.ben.prayerschedule.data.repository

import com.ben.prayerschedule.data.api.AppResponse
import io.reactivex.Flowable

interface MainDataSource {

    fun getCalendarByCity(
        city: String,
        country: String,
        month: Int,
        year: Int
    ): Flowable<AppResponse>

    companion object {
        val repository: MainDataSource = MainRepository()
    }
}