package com.cricketsports.dafamobile.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cricketsports.dafamobile.R
import com.cricketsports.dafamobile.datamodel.Data
import java.text.SimpleDateFormat
import java.util.*


class RVAdapter (val context: Context, val data: List<Data>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder (itemView){

        var teamNames: TextView = itemView.findViewById(R.id.teamNames)
        var scheduledDate: TextView = itemView.findViewById(R.id.scheduledDate)
        var scheduledWeekDay: TextView = itemView.findViewById(R.id.scheduledWeekDay)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.raw_vp_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val main: Data = data[position]

        holder.teamNames.text = main.name

        val formattedDate =
            SimpleDateFormat("dd.MM.yyyy HH:mm")
                .format(
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .parse(main.dateTimeGMT)!!
                )

        holder.scheduledDate.text = formattedDate

        val generatedWeekDay =
            SimpleDateFormat("yyyy-MM-dd").parse(main.date)

        holder.scheduledWeekDay.text =
            android.text.format.DateFormat
                .format("EEEE", generatedWeekDay!!.time)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}