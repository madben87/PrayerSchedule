package com.ben.prayerschedule.ui.main_screen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ProgressBar
import androidx.core.view.setPadding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ben.prayerschedule.MyApplication
import com.ben.prayerschedule.R
import com.ben.prayerschedule.data.model.CalendarType
import com.ben.prayerschedule.extensions.DP
import com.ben.prayerschedule.extensions.obtainViewModel
import com.ben.prayerschedule.ui.ProgressActivity
import com.ben.prayerschedule.ui.detail_screen.DetailActivity
import com.ben.prayerschedule.util.AppItemDecorator
import com.ben.prayerschedule.util.StateEnum
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ProgressActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

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

                adapter.actionItemClick = {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TIMESTAMP, it)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.settings)?.actionView.let {
            val v = it as ImageView?
            v?.let {
                it.setImageDrawable(resources.getDrawable(R.drawable.ic_settings_white_24dp))
                it.setPadding(this.DP(16).toInt())
                it.setOnClickListener {
                    showPopUp(it)
                }
            }
        }
        return true
    }

    private fun showPopUp(view: View) {
        val popUp = PopupMenu(this, view)
        popUp.inflate(R.menu.locale_menu)
        popUp.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.gregorianBtn -> {
                    MyApplication.CALENDAR_TYPE = CalendarType.GREGORIAN
                    getCurrentViewModel().adapter.notifyDataSetChanged()
                    return@setOnMenuItemClickListener true
                }
                R.id.hijriBtn -> {
                    MyApplication.CALENDAR_TYPE = CalendarType.HIJRI
                    getCurrentViewModel().adapter.notifyDataSetChanged()
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }
        popUp.show()
    }

    override fun getProgressBar(): ProgressBar {
        return progressBar
    }

    override fun getCurrentViewModel(): MainViewModel {
        return obtainViewModel(MainViewModel::class.java)
    }
}
