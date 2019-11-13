package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class DailySchedule(
    @SerializedName("timings")
    var timings: Timings? = null,
    @SerializedName("date")
    var date: Date? = null,
    @SerializedName("meta")
    var meta: Meta? = null
) : RealmObject()