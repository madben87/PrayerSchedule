package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("params")
    val params: Params? = null
)
