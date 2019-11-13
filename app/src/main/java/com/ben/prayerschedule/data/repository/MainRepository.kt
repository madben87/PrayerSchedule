package com.ben.prayerschedule.data.repository

import com.ben.prayerschedule.data.api.Api
import com.ben.prayerschedule.data.api.AppResponse
import com.ben.prayerschedule.data.api.MainApi
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class MainRepository : MainDataSource {

    private val DEFAULT_METHOD = 2

    override fun getCalendarByCity(
        city: String,
        country: String,
        month: Int,
        year: Int
    ): Flowable<AppResponse> {
        return Api.client.create(MainApi::class.java)
            .getCalendarByCity(city, country, DEFAULT_METHOD, month, year).map {
                return@map it
            }
            .doOnError { error ->
                return@doOnError
            }
            .toFlowable(BackpressureStrategy.BUFFER)
    }
}