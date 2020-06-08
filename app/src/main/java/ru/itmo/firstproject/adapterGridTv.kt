package ru.itmo.firstproject

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class adapterGridTv(ctx: Context, gridTvArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<adapterGridTv.viewHolder>() {


    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    init {

        gridTvArrayList = gridTvArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterGridTv.viewHolder {

        val view = inflater.inflate(R.layout.final_item, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterGridTv.viewHolder, position: Int) {


        holder.tv.text = gridTvArrayList[position].getAiValue()


    }

    override fun getItemCount(): Int {
        return gridTvArrayList.size
    }

    inner class viewHolder(textView: View) : RecyclerView.ViewHolder(textView) {

        var tv: TextView = textView.findViewById(R.id.tvid) as TextView

      //  init {


/*            textView.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                    gridTvArrayList[adapterPosition].setAiValue(textView.text.toString())
                }

                override fun afterTextChanged(editable: Editable) {

                }
            })*/

    //    }

    }

    companion object {
        lateinit var gridTvArrayList: MutableList<ai>
    }
}