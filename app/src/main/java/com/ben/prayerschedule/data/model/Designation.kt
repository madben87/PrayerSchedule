package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Designation(
    @SerializedName("expanded")
    val expanded: String? = null,
    @SerializedName("abbreviated")
    val abbreviated: String? = null
)