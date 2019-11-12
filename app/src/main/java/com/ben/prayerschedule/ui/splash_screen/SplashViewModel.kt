package com.ben.prayerschedule.ui.splash_screen

import android.app.Application
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum

class SplashViewModel(context: Application) : MyAndroidViewModel(context) {

    var location: Location? = null

    val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location?) {
            location = p0
            state.value = StateEnum.FIRST_ACTION
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

    init {
        bind()
    }

    private fun bind() {
        state.value = StateEnum.LOADING
    }
}