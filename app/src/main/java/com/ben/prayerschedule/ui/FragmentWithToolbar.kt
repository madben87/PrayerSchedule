package com.ben.prayerschedule.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ben.prayerschedule.extensions.dismissDialogs
import com.ben.prayerschedule.extensions.showError
import com.ben.prayerschedule.extensions.showProgressDialog
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum
import kotlinx.android.synthetic.main.activity_main.*

abstract class FragmentWithToolbar : Fragment() {

    abstract fun getCurrentViewModel(): MyAndroidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                activity?.onBackPressed()
        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val activity = activity as AppCompatActivity

        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val actionBar = activity.supportActionBar
        actionBar?.setDisplayShowHomeEnabled(true)

        getCurrentViewModel().state.observe(this@FragmentWithToolbar, Observer {
            when (it) {
                StateEnum.FIRST_ACTION -> {
                    dismissDialogs()
                }
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
                }
                else -> {
                }
            }
        })

    }

}