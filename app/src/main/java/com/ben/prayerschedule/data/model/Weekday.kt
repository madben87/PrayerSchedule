package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Weekday(
    @field:SerializedName("ar")
    var ar: String? = null,
    @field:SerializedName("en")
    var en: String? = null
) : RealmObject()