package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Offset(
    @SerializedName("sunset")
    val sunset: Int? = null,
    @SerializedName("asr")
    val asr: Int? = null,
    @SerializedName("isha")
    val isha: Int? = null,
    @SerializedName("fajr")
    val fajr: Int? = null,
    @SerializedName("dhuhr")
    val dhuhr: Int? = null,
    @SerializedName("maghrib")
    val maghrib: Int? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("midnight")
    val midnight: Int? = null,
    @SerializedName("imsak")
    val imsak: Int? = null
)
