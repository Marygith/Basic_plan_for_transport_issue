package ru.itmo.firstproject

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

class adapterA(ctx: Context, aiArrayLists: MutableList<ai>) :
    RecyclerView.Adapter<adapterA.viewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    init {

        aiArrayList = aiArrayLists
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterA.viewHolder {

        val view = inflater.inflate(R.layout.itemai, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: adapterA.viewHolder, position: Int) {


        holder.editText.setText(aiArrayList[position].getAiValue())


    }

    override fun getItemCount(): Int {
        return aiArrayList.size
    }

    inner class viewHolder(editView: View) : RecyclerView.ViewHolder(editView) {

        var editText: EditText

        init {

            editText = editView.findViewById(R.id.aiid) as EditText

            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                    aiArrayList[adapterPosition].setAiValue(editText.text.toString())
                }

                override fun afterTextChanged(editable: Editable) {

                }
            })

        }

    }

    companion object {
        lateinit var aiArrayList: MutableList<ai>
    }
}
