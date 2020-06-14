package ru.itmo.firstproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_second)
        if(MainActivity.flag == 1) {
            aiArrayList = BufferActivity.createArrayFromInt(listOf(120, 30, 40, 60) as MutableList<Int>)
        }
        else {
            aiArrayList = MutableList(1) { ai() }
           // currentAdapter = adapterA(this, aiArrayList)
            //recycler.adapter = currentAdapter
            btn.setOnClickListener {
                aiArrayList.add(ai())
                currentAdapter!!.notifyDataSetChanged()
                if (aiArrayList.size > 1) {
                    delete_ai.isClickable = true
                    delete_ai.background.setTint(Color.parseColor("#023A53"))
                }
            }
            delete_ai.setOnClickListener {
                if (aiArrayList.size > 1) {
                    aiArrayList.removeAt(aiArrayList.size - 1)
                    currentAdapter!!.notifyDataSetChanged()
                }
                if (aiArrayList.size <= 1) {
                    delete_ai.isClickable = false
                    delete_ai.background.setTint(Color.parseColor("#9CAFB8"))
                }

            }
        }
        currentAdapter = adapterA(this, aiArrayList)
        recycler.adapter = currentAdapter
        recycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        btnBack.setOnClickListener {
            var checker = 0
            for (item in adapterA.aiArrayList) {
                if (!item.getAiValue().matches("[0-9]+".toRegex()) || item.getAiValue()
                        .matches("[0]+".toRegex())
                ) {
                    btnWarning.text = StringBuffer("Please enter only positive integer")
                    checker = -1
                    break
                }
            }
            if (checker == 0) {
                btnWarning.clearComposingText()
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private var currentAdapter: adapterA? = null
    lateinit var aiArrayList: MutableList<ai>
}
