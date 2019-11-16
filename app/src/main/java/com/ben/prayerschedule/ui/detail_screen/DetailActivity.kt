package com.ben.prayerschedule.ui.detail_screen

import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ben.prayerschedule.R
import com.ben.prayerschedule.extensions.obtainViewModel
import com.ben.prayerschedule.ui.ProgressActivity
import com.ben.prayerschedule.util.AppItemDecorator
import com.ben.prayerschedule.util.StateEnum
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.progressBar

class DetailActivity : ProgressActivity() {

    companion object {
        val TIMESTAMP = "time"
    }

    lateinit var viewModel: DetailViewModel
    var timestamp = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = getCurrentViewModel()
        viewModel.apply {

            intent?.let {
                if (it.hasExtra(TIMESTAMP)) {
                    timestamp = it.getStringExtra(TIMESTAMP) ?: ""
                }
            }

            state.observe(this@DetailActivity, Observer {
                when (it) {
                    StateEnum.LOADING -> {

                    }
                    StateEnum.FIRST_ACTION -> {
                        updateView(timestamp)
                    }
                    StateEnum.COMPLETE -> {

                    }
                    StateEnum.ERROR -> {

                        state.value = StateEnum.NONE
                    }
                    else -> {
                    }
                }
            })

            pryerList.layoutManager =
                LinearLayoutManager(
                    this@DetailActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )

            pryerList.adapter = adapter

            this@DetailActivity.resources.getDrawable(R.drawable.divider_gray, null)
                ?.let {
                    val itemDecor = AppItemDecorator(it)
                    pryerList.addItemDecoration(itemDecor)
                }
        }
    }

    override fun getProgressBar(): ProgressBar {
        return progressBar
    }

    override fun getCurrentViewModel(): DetailViewModel {
        return obtainViewModel(DetailViewModel::class.java)
    }
}