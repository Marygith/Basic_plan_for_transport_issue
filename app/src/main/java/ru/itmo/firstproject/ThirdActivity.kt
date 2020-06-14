package ru.itmo.firstproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btnBack.setOnClickListener {
            var checker = 0
            for (item in adapterB.biArrayList) {
                if (!item.getAiValue().matches("[0-9]+".toRegex()) || item.getAiValue()
                        .matches("[0]+".toRegex())
                ) {
                    btnWarning.text = StringBuffer("Please enter only positive integer")
                    checker = -1
                    break
                }
            }
            if (checker == 0) {
                if (adapterA.aiArrayList.sumBy {
                        it.getAiValue().toInt()
                    } == adapterB.biArrayList.sumBy { it.getAiValue().toInt() }) {
                    btnWarning.clearComposingText()
                    val intent = Intent(this, ForthActivity::class.java)
                    startActivity(intent)
                } else {
                    btnWarning.text =
                        StringBuffer("Amounts of required and granted products are not equal")
                }
            }
        }
        delete_bi.isClickable = false


        biArrayList = MutableList(1) { ai() }
        currentAdapter = adapterB(this, biArrayList)

        recycler.adapter = currentAdapter
        btn.setOnClickListener {
            biArrayList.add(ai())
            currentAdapter!!.notifyDataSetChanged()
            if (biArrayList.size > 1) {
                delete_bi.isClickable = true
                delete_bi.background.setTint(Color.parseColor("#034070"))
            }
        }
        delete_bi.setOnClickListener {
            if (biArrayList.size > 1) {
                biArrayList.removeAt(biArrayList.size - 1)
                currentAdapter!!.notifyDataSetChanged()
            }
            if (biArrayList.size <= 1) {
                delete_bi.isClickable = false
                delete_bi.background.setTint(Color.parseColor("#BAC8D3"))
            }

        }
        recycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
    }

    private var currentAdapter: adapterB? = null
    lateinit var biArrayList: MutableList<ai>
}
