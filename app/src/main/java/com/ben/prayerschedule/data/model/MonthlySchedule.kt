package com.ben.prayerschedule.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MonthlySchedule(
    @PrimaryKey
    var id: Int = 0,
    var schedules: RealmList<DailySchedule>? = null
) : RealmObject()