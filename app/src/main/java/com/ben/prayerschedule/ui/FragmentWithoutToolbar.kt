package com.ben.prayerschedule.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ben.prayerschedule.extensions.dismissDialogs
import com.ben.prayerschedule.extensions.showError
import com.ben.prayerschedule.extensions.showProgressDialog
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum

abstract class FragmentWithoutToolbar : Fragment() {

    abstract fun getCurrentViewModel(): MyAndroidViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        getCurrentViewModel().state.observe(this@FragmentWithoutToolbar, Observer {
            when (it) {
                StateEnum.LOADING -> {
                    showProgressDialog()
                }
                StateEnum.COMPLETE -> {
                    dismissDialogs()
                }
                StateEnum.ERROR -> {
                    dismissDialogs()
                    getCurrentViewModel().errorMessage.get()?.let { errorText ->
                        showError(errorText)
                    }
                    getCurrentViewModel().state.value = StateEnum.NONE
                }
                else -> {
                }
            }
        })

    }
}