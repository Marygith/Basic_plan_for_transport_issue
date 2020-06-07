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
import java.util.ArrayList


class adapterB(ctx: Context, biArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<adapterB.viewHolder>() {

    val inflater: LayoutInflater
//    var aiArrayList: MutableList<ai>

    init {

        inflater = LayoutInflater.from(ctx)
        biArrayList = biArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterB.viewHolder {

        val view = inflater.inflate(R.layout.itembi, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterB.viewHolder, position: Int) {


        holder.editText.setText(biArrayList[position].getAiValue())


    }

    override fun getItemCount(): Int {
        return biArrayList.size
    }

    inner class viewHolder(editView: View) : RecyclerView.ViewHolder(editView) {

        var editText: EditText = editView.findViewById(R.id.biid) as EditText

        init {

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                    biArrayList[adapterPosition].setAiValue(editText.text.toString())
                }

                override fun afterTextChanged(editable: Editable) {

                }
            })

        }

    }

    companion object {
        lateinit var biArrayList: MutableList<ai>
    }
}