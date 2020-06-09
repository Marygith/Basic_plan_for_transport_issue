package ru.itmo.firstproject

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class adapterBTv(ctx: Context, bTvArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<adapterBTv.viewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    init {

        bTvArrayList = bTvArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterBTv.viewHolder {

        val view = inflater.inflate(R.layout.b_tv, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterBTv.viewHolder, position: Int) {


        holder.tv.text = bTvArrayList[position].getAiValue()


    }

    override fun getItemCount(): Int {
        return bTvArrayList.size
    }

    inner class viewHolder(textView: View) : RecyclerView.ViewHolder(textView) {

        var tv: TextView = textView.findViewById(R.id.tvid) as TextView



    }

    companion object {
        lateinit var bTvArrayList: MutableList<ai>
    }
}