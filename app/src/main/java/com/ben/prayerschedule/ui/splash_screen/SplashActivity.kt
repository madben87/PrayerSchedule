package com.ben.prayerschedule.ui.splash_screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.ben.prayerschedule.R
import com.ben.prayerschedule.extensions.obtainViewModel
import com.ben.prayerschedule.ui.ProgressActivity
import com.ben.prayerschedule.ui.main_screen.MainActivity
import com.ben.prayerschedule.util.DialogFactory
import com.ben.prayerschedule.util.DialogFactoryType
import com.ben.prayerschedule.util.PermissionUtils
import com.ben.prayerschedule.util.StateEnum
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : ProgressActivity() {

    lateinit var viewModel: SplashViewModel

    override fun getProgressBar(): ProgressBar {
        return progressBar
    }

    override fun getCurrentViewModel(): SplashViewModel {
        return obtainViewModel(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = getCurrentViewModel()
        viewModel.apply {

            state.observe(this@SplashActivity, Observer {
                when (it) {
                    StateEnum.LOADING -> {

                    }
                    StateEnum.COMPLETE -> {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                    StateEnum.ERROR -> {

                        state.value = StateEnum.NONE
                    }
                    else -> {
                    }
                }
            })
        }

        checkPermission(Runnable { setLocationListener() },
            Runnable { showDialogNoPermission() },
            PermissionUtils.ACCESS_COARSE_LOCATION,
            PermissionUtils.LOCATION_PERMISSION
        )
    }

    @SuppressLint("MissingPermission")
    private fun setLocationListener() {
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0L,
            0f,
            viewModel.locationListener
        )
    }

    private fun showDialogNoPermission() {
        DialogFactory(DialogFactoryType.ERROR, this).setInfo(
            getString(R.string.Global_error),
            this.getText(R.string.Global_close).toString()
        ).show()
    }
}
