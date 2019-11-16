package com.ben.prayerschedule.ui.main_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ben.prayerschedule.MyApplication
import com.ben.prayerschedule.R
import com.ben.prayerschedule.data.model.CalendarType
import com.ben.prayerschedule.data.model.DailySchedule
import kotlinx.android.synthetic.main.item_schedule.view.*

class MainAdapter(var context: Context) : RecyclerView.Adapter<MainAdapter.DateViewHolder>() {

    companion object {
        var todayPosition = 0
    }

    var items = ArrayList<DailySchedule>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var actionItemClick: (timestamp: String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_schedule,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(items.get(position), actionItemClick, position)
    }


    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val date = itemView.date
        fun bind(
            schedule: DailySchedule,
            actionItemClick: (timestamp: String) -> Unit,
            position: Int
        ) {

            date.isActivated = position == todayPosition

            when (MyApplication.CALENDAR_TYPE) {
                CalendarType.GREGORIAN -> {
                    date.text = String.format(
                        "%s %s %s",
                        schedule.date?.gregorian?.day,
                        schedule.date?.gregorian?.month?.en,
                        schedule.date?.gregorian?.year
                    )
                }
                CalendarType.HIJRI -> {
                    date.text = String.format(
                        "%s %s %s",
                        schedule.date?.hijri?.day,
                        schedule.date?.hijri?.month?.en,
                        schedule.date?.hijri?.year
                    )
                }
            }

            date.setOnClickListener {
                schedule.date?.timestamp?.let {
                    actionItemClick.invoke(it)
                }
            }
        }
    }
}