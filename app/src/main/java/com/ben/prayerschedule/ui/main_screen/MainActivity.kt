package com.ben.prayerschedule.ui.main_screen

import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ben.prayerschedule.R
import com.ben.prayerschedule.extensions.obtainViewModel
import com.ben.prayerschedule.ui.ProgressActivity
import com.ben.prayerschedule.util.AppItemDecorator
import com.ben.prayerschedule.util.StateEnum
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ProgressActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getCurrentViewModel()
        viewModel.apply {

            state.observe(this@MainActivity, Observer {
                when (it) {
                    StateEnum.LOADING -> {

                    }
                    StateEnum.FIRST_ACTION -> {
                        schedulesList.layoutManager =
                            LinearLayoutManager(
                                this@MainActivity,
                                LinearLayoutManager.VERTICAL,
                                false
                            )

                        schedulesList.adapter = adapter
                        adapter.notifyDataSetChanged()
                        schedulesList.scrollToPosition(todayPosition)

                        this@MainActivity.resources.getDrawable(R.drawable.divider_gray, null)
                            ?.let {
                                val itemDecor = AppItemDecorator(it)
                                schedulesList.addItemDecoration(itemDecor)
                            }
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
        }
    }

    override fun getProgressBar(): ProgressBar {
        return progressBar
    }

    override fun getCurrentViewModel(): MainViewModel {
        return obtainViewModel(MainViewModel::class.java)
    }
}
