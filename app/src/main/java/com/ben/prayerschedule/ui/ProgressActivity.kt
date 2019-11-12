package com.ben.prayerschedule.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ben.prayerschedule.extensions.setVisible
import com.ben.prayerschedule.util.CheckPermissionsUtils
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum

abstract class ProgressActivity : AppCompatActivity() {

    abstract fun getProgressBar(): ProgressBar
    abstract fun getCurrentViewModel(): MyAndroidViewModel

    private var runnableAction: Runnable? = null
    private var cancelAction: Runnable? = null

    fun checkPermission(
        permitAction: Runnable,
        cancelAction: Runnable?,
        vararg permissions: String
    ) {
        this.runnableAction = permitAction
        this.cancelAction = cancelAction
        if (CheckPermissionsUtils.checkPermission(this, permissions))
            permitAction.run()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        CheckPermissionsUtils.handlePermissions(
            requestCode,
            grantResults,
            runnableAction!!,
            cancelAction
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getCurrentViewModel().state.observe(this@ProgressActivity, Observer {
            when (it) {
                StateEnum.FIRST_ACTION -> {
                    getProgressBar().setVisible(false)
                }
                StateEnum.LOADING -> {
                    getProgressBar().setVisible(true)
                }
                StateEnum.COMPLETE -> {
                    getProgressBar().setVisible(false)
                }
                StateEnum.ERROR -> {
                    getProgressBar().setVisible(false)
                }
                else -> {
                }
            }
        })
    }
}