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
            if(adapterA.aiArrayList.sumBy { it.getAiValue().toInt() } == adapterB.biArrayList.sumBy { it.getAiValue().toInt() })
            {textView2.clearComposingText()
                val intent = Intent(this, ForthActivity::class.java)
                startActivity(intent)}
            else{
textView2.text = StringBuffer("Amounts of required and granted products are not equal")

            }

        }

        biArrayList= MutableList(1) {ai()}
        currentAdapter = adapterB(this, biArrayList)
        // aiArrayList = MutableList(1) { x -> ai() }

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
//recycler.layoutManager = GridLayoutManager(applicationContext, GridLayoutManager.HORIZONTAL)
    }
}
