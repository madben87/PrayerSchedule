package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("hijri")
    val hijri: Hijri? = null,
    @SerializedName("gregorian")
    val gregorian: Gregorian? = null
)