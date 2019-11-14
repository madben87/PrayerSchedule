package com.ben.prayerschedule.ui.main_screen

import android.app.Application
import com.ben.prayerschedule.data.model.DailySchedule
import com.ben.prayerschedule.realm.RealmManager
import com.ben.prayerschedule.util.MyAndroidViewModel
import com.ben.prayerschedule.util.StateEnum
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

class MainViewModel(val context: Application) : MyAndroidViewModel(context) {

    val adapter = MainAdapter(context)
    var todayPosition = 0

    init {
        bind()
    }

    private fun bind() {
        state.value = StateEnum.LOADING
        RealmManager.getInstance().getSchedule(LocalDate.now())?.schedules?.let {
            val list = ArrayList<DailySchedule>()
            list.addAll(it.asIterable())

            list.forEachIndexed { index, element ->
                element.date?.timestamp?.toLong()?.let {
                    val dateValue = Instant.ofEpochMilli(it * 1000).atZone(ZoneId.systemDefault())
                        .toLocalDate()

                    if (dateValue.equals(LocalDate.now()))
                        todayPosition = index
                }
            }
            adapter.items = list
            MainAdapter.todayPosition = todayPosition
        }
        state.value = StateEnum.FIRST_ACTION
    }
}