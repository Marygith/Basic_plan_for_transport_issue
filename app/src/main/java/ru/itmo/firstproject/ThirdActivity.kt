package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {


    private var currentAdapter: adapterB? = null // EditAdapter
    lateinit var biArrayList: MutableList<ai> // EditModel  EditModelArrayList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        btnBack.setOnClickListener {
            var checker = 0
            for (item in adapterB.biArrayList) {
                if (!item.getAiValue().matches("[0-9]+".toRegex())) {
                    btnWarning.text = StringBuffer("Please enter only integer value")
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

        biArrayList = MutableList(1) { ai() }
        currentAdapter = adapterB(this, biArrayList)

        recycler.adapter = currentAdapter
        btn.setOnClickListener {
            biArrayList.add(ai())
            currentAdapter!!.notifyDataSetChanged()

        }
        recycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
    }
}
