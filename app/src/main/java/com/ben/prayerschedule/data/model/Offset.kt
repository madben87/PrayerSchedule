package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Offset(
    @SerializedName("sunset")
    var sunset: Int? = null,
    @SerializedName("asr")
    var asr: Int? = null,
    @SerializedName("isha")
    var isha: Int? = null,
    @SerializedName("fajr")
    var fajr: Int? = null,
    @SerializedName("dhuhr")
    var dhuhr: Int? = null,
    @SerializedName("maghrib")
    var maghrib: Int? = null,
    @SerializedName("sunrise")
    var sunrise: Int? = null,
    @SerializedName("midnight")
    var midnight: Int? = null,
    @SerializedName("imsak")
    var imsak: Int? = null
) : RealmObject()
