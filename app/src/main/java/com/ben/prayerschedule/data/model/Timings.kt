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
) : RealmObject() {

    fun getItems(): List<Pair<String, String>> {
        val items = ArrayList<Pair<String, String>>()

        val mock = "--:--"

        items.add(Pair("Fajr", fajr ?: mock))
        items.add(Pair("Sunrise", sunrise ?: mock))
        items.add(Pair("Dhuhr", dhuhr ?: mock))
        items.add(Pair("Asr", asr ?: mock))
        items.add(Pair("Sunset", sunset ?: mock))
        items.add(Pair("Maghrib", maghrib ?: mock))
        items.add(Pair("Isha", isha ?: mock))
        items.add(Pair("Imsak", imsak ?: mock))
        items.add(Pair("Midnight", midnight ?: mock))

        return items
    }
}