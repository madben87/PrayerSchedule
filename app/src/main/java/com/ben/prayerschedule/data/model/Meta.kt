package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("method")
    val method: Method? = null,
    @SerializedName("offset")
    val offset: Offset? = null,
    @SerializedName("school")
    val school: String? = null,
    @SerializedName("timezone")
    val timezone: String? = null,
    @SerializedName("midnightMode")
    val midnightMode: String? = null,
    @SerializedName("latitude")
    val latitude: Double? = null,
    @SerializedName("longitude")
    val longitude: Double? = null,
    @SerializedName("latitudeAdjustmentMethod")
    val latitudeAdjustmentMethod: String? = null
)
