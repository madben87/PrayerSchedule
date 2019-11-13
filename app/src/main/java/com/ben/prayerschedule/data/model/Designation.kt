package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Designation(
    @SerializedName("expanded")
    var expanded: String? = null,
    @SerializedName("abbreviated")
    var abbreviated: String? = null
) : RealmObject()