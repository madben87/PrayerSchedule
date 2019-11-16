package com.ben.prayerschedule.ui.detail_screen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ben.prayerschedule.R
import kotlinx.android.synthetic.main.activity_detail.view.title
import kotlinx.android.synthetic.main.item_pryer.view.*

class DetailAdapter(var context: Context) : RecyclerView.Adapter<DetailAdapter.DateViewHolder>() {

    var items: List<Pair<String, String>> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        return DateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pryer,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(items.get(position))
    }


    class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.title
        val time = itemView.time

        fun bind(data: Pair<String, String>) {

            title.text = data.first
            time.text = data.second
        }
    }
}