package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Meta(
    @SerializedName("method")
    var method: Method? = null,
    @SerializedName("offset")
    var offset: Offset? = null,
    @SerializedName("school")
    var school: String? = null,
    @SerializedName("timezone")
    var timezone: String? = null,
    @SerializedName("midnightMode")
    var midnightMode: String? = null,
    @SerializedName("latitude")
    var latitude: Double? = null,
    @SerializedName("longitude")
    var longitude: Double? = null,
    @SerializedName("latitudeAdjustmentMethod")
    var latitudeAdjustmentMethod: String? = null
) : RealmObject()
