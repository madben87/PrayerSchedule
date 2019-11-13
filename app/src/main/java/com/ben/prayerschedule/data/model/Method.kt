package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Method(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("params")
    var params: Params? = null
) : RealmObject()
