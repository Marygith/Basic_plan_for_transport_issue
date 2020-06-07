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

class gridCellAdapter (ctx: Context, gridArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<gridCellAdapter.viewHolder>() {

    val inflater: LayoutInflater = LayoutInflater.from(ctx)
//    var gridArrayList: MutableList<ai>

    init {

        gridArrayList = gridArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): gridCellAdapter.viewHolder {

        val view = inflater.inflate(R.layout.grid_cell, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: gridCellAdapter.viewHolder, position: Int) {


        holder.editText.setText(gridArrayList[position].getAiValue())


    }

    override fun getItemCount(): Int {
        return gridArrayList.size
    }

    inner class viewHolder(editView: View) : RecyclerView.ViewHolder(editView) {

        var editText: EditText

        init {

            editText = editView.findViewById(R.id.cellid) as EditText

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                    gridArrayList[adapterPosition].setAiValue(editText.text.toString())
                }

                override fun afterTextChanged(editable: Editable) {

                }
            })

        }

    }

    companion object {
        lateinit var gridArrayList: MutableList<ai>
    }
}