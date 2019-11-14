package com.example.hologo.prefs

import com.ben.prayerschedule.prefs.PrefManager
import com.ben.prayerschedule.prefs.StringPref

object LocationPrefs : PrefManager("global_location") {

    @JvmStatic
    var city by StringPref()

    @JvmStatic
    var country by StringPref()
}