package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class Hijri(
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("month")
    var month: Month? = null,
    @SerializedName("holidays")
    var holidays: RealmList<String?>? = null,
    @SerializedName("year")
    var year: String? = null,
    @SerializedName("format")
    var format: String? = null,
    @SerializedName("weekday")
    var weekday: Weekday? = null,
    @SerializedName("designation")
    var designation: Designation? = null,
    @SerializedName("day")
    var day: String? = null
) : RealmObject()