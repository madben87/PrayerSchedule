package com.ben.prayerschedule.data.api

import com.ben.prayerschedule.data.model.DailySchedule
import com.google.gson.annotations.SerializedName

data class AppResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("status")
    var status: String,
    @SerializedName("data")
    var data: List<DailySchedule>
)