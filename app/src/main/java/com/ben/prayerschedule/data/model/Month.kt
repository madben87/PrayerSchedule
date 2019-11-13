package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Month(
    @SerializedName("number")
    var number: Int? = null,
    @SerializedName("ar")
    var ar: String? = null,
    @SerializedName("en")
    var en: String? = null
) : RealmObject()