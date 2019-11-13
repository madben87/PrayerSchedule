package com.ben.prayerschedule.realm

import com.ben.prayerschedule.data.model.DateUtil
import com.ben.prayerschedule.data.model.MonthlySchedule
import io.realm.Realm
import org.threeten.bp.LocalDate

class RealmManager private constructor() {

    fun saveSchedule(data: MonthlySchedule) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insertOrUpdate(data)
        realm.commitTransaction()
    }

    fun getSchedules(): MutableList<MonthlySchedule> {
        val realm = Realm.getDefaultInstance()
        val temp = realm.where(MonthlySchedule::class.java).findAll()
        return if (temp != null) realm.copyFromRealm(temp) else ArrayList()
    }

    fun getSchedule(date: LocalDate): MonthlySchedule? {
        val realm = Realm.getDefaultInstance()
        val id = DateUtil.getKey()
        val temp = realm.where(MonthlySchedule::class.java).findAll()
        return realm.copyFromRealm(temp[0])
    }

    companion object {
        private var instant: RealmManager? = null
        fun getInstance(): RealmManager {
            if (instant == null)
                instant = RealmManager()
            return instant!!
        }
    }
}

