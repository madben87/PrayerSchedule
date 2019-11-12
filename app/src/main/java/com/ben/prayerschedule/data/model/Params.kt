package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Params(
    @SerializedName("isha")
    val isha: Int? = null,
    @SerializedName("fajr")
    val fajr: Int? = null
)
