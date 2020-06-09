package ru.itmo.firstproject

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class adapterATv(ctx: Context, aTvArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<adapterATv.viewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    init {

        aTvArrayList = aTvArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterATv.viewHolder {

        val view = inflater.inflate(R.layout.a_tv, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterATv.viewHolder, position: Int) {


        holder.tv.text = aTvArrayList[position].getAiValue()


    }

    override fun getItemCount(): Int {
        return aTvArrayList.size
    }

    inner class viewHolder(textView: View) : RecyclerView.ViewHolder(textView) {

        var tv: TextView = textView.findViewById(R.id.tvid) as TextView

    }

    companion object {
        lateinit var aTvArrayList: MutableList<ai>
    }
}