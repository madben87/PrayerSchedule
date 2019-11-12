package com.ben.prayerschedule.util

import android.util.Log
import com.ben.prayerschedule.BuildConfig

object Logs {

    fun d(message: String) {
        if (BuildConfig.DEBUG)
            Log.d("HoLoGo", message)
    }
}
