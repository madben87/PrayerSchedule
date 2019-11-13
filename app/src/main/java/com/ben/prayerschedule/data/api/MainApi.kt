package com.ben.prayerschedule.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    // http://api.aladhan.com/v1/calendarByCity?city=London&country=United Kingdom&method=2&month=04&year=2017

    @GET("v1/calendarByCity")
    fun getCalendarByCity(
        @Query("city") city: String, @Query("country") country: String
        , @Query("method") method: Int, @Query("month") month: Int, @Query("year") year: Int
    ):
            Observable<AppResponse>
}