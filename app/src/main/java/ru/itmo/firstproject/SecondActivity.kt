package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.itemai.*
class SecondActivity : AppCompatActivity() {



    private var currentAdapter: adapterA? = null // EditAdapter
    lateinit var aiArrayList: MutableList<ai> // EditModel  EditModelArrayList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btnNext.setOnClickListener {
val intent = Intent(this, ThirdActivity::class.java)
startActivity(intent)
        }

        aiArrayList= MutableList(1) {ai()}
        currentAdapter = adapterA(this, aiArrayList)
       // aiArrayList = MutableList(1) { x -> ai() }

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
}
