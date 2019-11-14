package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Date(
    @SerializedName("readable")
    var readable: String? = null,
    @SerializedName("timestamp")
    var timestamp: String? = null,
    @SerializedName("hijri")
    var hijri: Hijri? = null,
    @SerializedName("gregorian")
    var gregorian: Gregorian? = null
) : RealmObject()