package ru.itmo.firstproject

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.ArrayList

class adapterATV(ctx: Context, ATVArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<adapterATV.viewHolder>() {


    val inflater: LayoutInflater
//    var ATVArrayList: MutableList<ai>

    init {

        inflater = LayoutInflater.from(ctx)
        ATVArrayList = ATVArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterATV.viewHolder {

        val view = inflater.inflate(R.layout.final_item, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterATV.viewHolder, position: Int) {


        holder.tv.setText(ATVArrayList[position].getAiValue())


    }

    override fun getItemCount(): Int {
        return ATVArrayList.size
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

                    ATVArrayList[adapterPosition].setAiValue(textView.text.toString())
                }

                override fun afterTextChanged(editable: Editable) {

                }
            })*/

    //    }

    }

    companion object {
        lateinit var ATVArrayList: MutableList<ai>
    }
}