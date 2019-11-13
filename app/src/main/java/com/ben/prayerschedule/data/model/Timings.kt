package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Timings(
    @SerializedName("Asr")
    var asr: String? = null,
    @SerializedName("Dhuhr")
    var dhuhr: String? = null,
    @SerializedName("Fajr")
    var fajr: String? = null,
    @SerializedName("Imsak")
    var imsak: String? = null,
    @SerializedName("Isha")
    var isha: String? = null,
    @SerializedName("Maghrib")
    var maghrib: String? = null,
    @SerializedName("Midnight")
    var midnight: String? = null,
    @SerializedName("Sunrise")
    var sunrise: String? = null,
    @SerializedName("Sunset")
    var sunset: String? = null
) : RealmObject()