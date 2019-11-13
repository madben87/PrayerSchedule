package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Params(
    @SerializedName("isha")
    var isha: Int? = null,
    @SerializedName("fajr")
    var fajr: Int? = null
) : RealmObject()
