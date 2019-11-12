/*
package com.ben.prayerschedule.realm

import com.ben.prayerschedule.model.Country
import com.ben.prayerschedule.model.FilterDashboard
import com.ben.prayerschedule.model.User
import io.realm.Realm
import io.realm.RealmModel

class RealmManager private constructor() {

    fun saveCountries(countries: ArrayList<Country>) {
        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insertOrUpdate(countries)
        realm.commitTransaction()
    }

    fun getCountries(): MutableList<Country> {
        var realm = Realm.getDefaultInstance()
        val temp = realm.where(Country::class.java).findAll()
        return if (temp != null) realm.copyFromRealm(temp) else ArrayList()
    }

    fun getCountry(id: Int): Country? {
        var realm = Realm.getDefaultInstance()
        val temp = realm.where(Country::class.java).equalTo("id", id).findFirst()
        return if (temp != null) realm.copyFromRealm(temp) else null
    }

    fun insertUser(user: User) {

        getUser(user.id)?.let {
            if (user.balanceCurrency.isEmpty() && it.balanceCurrency.isNotEmpty()) {
                user.balanceCurrency = it.balanceCurrency
            }
        }

        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.insertOrUpdate(user)
        realm.commitTransaction()
    }

    fun insertFilterModel(filterDashBoard: FilterDashboard) {
        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.delete(FilterDashboard::class.java)
        realm.insert(filterDashBoard)
        realm.commitTransaction()
    }

    fun getDashBoard(): FilterDashboard? {
        var realm = Realm.getDefaultInstance()
        val temp = realm.where(FilterDashboard::class.java).findFirst()
        return if (temp != null) realm.copyFromRealm(temp) else null
    }

    fun delete(clazz: Class<out RealmModel>) {
        var realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.delete(clazz)
        realm.commitTransaction()
    }

    fun getUser(id: Long): User? {
        var realm = Realm.getDefaultInstance()
        val temp = realm.where(User::class.java).equalTo("id", id).findFirst()
        return if (temp != null) realm.copyFromRealm(temp) else null
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

*/
