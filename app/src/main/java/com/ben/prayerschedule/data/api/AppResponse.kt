package com.ben.prayerschedule.data.api

import com.ben.prayerschedule.data.model.Date
import com.ben.prayerschedule.data.model.Meta
import com.ben.prayerschedule.data.model.Timings
import com.google.gson.annotations.SerializedName

data class AppResponse(
    @SerializedName("timings")
    var timings: Timings,
    @SerializedName("date")
    var date: Date,
    @SerializedName("meta")
    var meta: Meta
)