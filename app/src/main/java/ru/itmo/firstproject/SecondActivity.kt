package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_second)

        btnBack.setOnClickListener {
            var checker = 0
            for (item in adapterA.aiArrayList) {
                if (!item.getAiValue().matches("[0-9]+".toRegex())) {
                    btnWarning.text = StringBuffer("Please enter only integer value")
                    checker = -1
                    break
                }
            }
            if(checker == 0) {
                btnWarning.clearComposingText()
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
            }
        }

        aiArrayList= MutableList(1) {ai()}
        currentAdapter = adapterA(this, aiArrayList)
        recycler.adapter = currentAdapter
        btn.setOnClickListener {
            aiArrayList.add(ai())
            currentAdapter!!.notifyDataSetChanged()
        }
        recycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
    }
    private var currentAdapter: adapterA? = null
    lateinit var aiArrayList: MutableList<ai>
}
