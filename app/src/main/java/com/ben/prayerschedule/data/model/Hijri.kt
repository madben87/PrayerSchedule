package com.ben.prayerschedule.data.model

import com.google.gson.annotations.SerializedName

data class Hijri(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("month")
    val month: Month? = null,
    @SerializedName("holidays")
    val holidays: List<String?>? = null,
    @SerializedName("year")
    val year: String? = null,
    @SerializedName("format")
    val format: String? = null,
    @SerializedName("weekday")
    val weekday: Weekday? = null,
    @SerializedName("designation")
    val designation: Designation? = null,
    @SerializedName("day")
    val day: String? = null
)