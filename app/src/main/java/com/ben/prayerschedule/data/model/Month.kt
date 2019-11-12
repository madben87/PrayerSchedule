package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Month(
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("ar")
    val ar: String? = null,
    @SerializedName("en")
    val en: String? = null
)