package com.ben.prayerschedule.ui.detail_screen

import android.app.Application
import com.ben.prayerschedule.data.model.DailySchedule
import com.ben.prayerschedule.realm.RealmManager
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum
import org.threeten.bp.LocalDate

class DetailViewModel(val context: Application) : MyAndroidViewModel(context) {

    var schedules = ArrayList<DailySchedule>()

    val adapter = DetailAdapter(context)

    init {
        bind()
    }

    private fun bind() {
        state.value = StateEnum.LOADING
        RealmManager.getInstance().getSchedule(LocalDate.now())?.schedules?.let {
            schedules.addAll(it.asIterable())
        }
        state.value = StateEnum.FIRST_ACTION
    }

    fun updateView(timestamp: String) {
        var schedule: DailySchedule? = null
        schedules.iterator().forEach {
            if (it.date?.timestamp.equals(timestamp)) {
                schedule = it
            }
        }

        schedule?.timings?.let {
            adapter.items = it.getItems()
        }
    }
}