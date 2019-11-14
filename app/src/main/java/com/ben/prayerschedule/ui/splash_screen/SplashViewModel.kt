package com.ben.prayerschedule.ui.splash_screen

import android.app.Application
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.Toast
import com.ben.prayerschedule.data.model.DailySchedule
import com.ben.prayerschedule.data.model.DateUtil
import com.ben.prayerschedule.data.model.MonthlySchedule
import com.ben.prayerschedule.data.repository.MainDataSource
import com.ben.prayerschedule.realm.RealmManager
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum
import com.example.hologo.prefs.LocationPrefs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.realm.RealmList
import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class SplashViewModel(val context: Application) : MyAndroidViewModel(context) {

    var location: Location? = null
    val geocoder = Geocoder(context, Locale.getDefault())
    var addressList = ArrayList<Address>()
    var city: String = ""
    var country: String = ""
    var isLocationLoaded = false

    val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location?) {
            if (!isLocationLoaded) {
                location = p0
                location?.let {
                    addressList =
                        geocoder.getFromLocation(it.latitude, it.longitude, 1) as ArrayList<Address>
                    if (addressList.size > 0) {
                        city = addressList.get(0).locality
                        country = addressList.get(0).countryName
                        isLocationLoaded = true

                        if (isSameLocation(city, country)) {
                            RealmManager.getInstance().getSchedule(LocalDate.now())?.let {
                                state.value = StateEnum.COMPLETE
                            } ?: kotlin.run {
                                getSchedule()
                            }
                        } else {
                            getSchedule()
                        }
                    }
                }

                state.value = StateEnum.FIRST_ACTION
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    fun isSameLocation(city: String, country: String): Boolean {
        var result = true
        if (LocationPrefs.city.isEmpty() || LocationPrefs.city != city) {
            LocationPrefs.city = city
            result = false
        }
        if (LocationPrefs.country.isEmpty() || LocationPrefs.country != country) {
            LocationPrefs.country = country
            result = false
        }
        return result
    }

    init {
        bind()
    }

    private fun bind() {
        state.value = StateEnum.LOADING
    }

    fun getSchedule() {
        MainDataSource.repository
            .getCalendarByCity(city, country, LocalDate.now().monthValue, LocalDate.now().year)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    val list = RealmList<DailySchedule>()
                    list.addAll(it.data)
                    RealmManager.getInstance()
                        .saveSchedule(MonthlySchedule(DateUtil.getKey(), list))
                },
                onError = {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                    state.value = StateEnum.ERROR
                },
                onComplete = {
                    state.value = StateEnum.COMPLETE
                }
            ).addTo(disposables)
    }
}