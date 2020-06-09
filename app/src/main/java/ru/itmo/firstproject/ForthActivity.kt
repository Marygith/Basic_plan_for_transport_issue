package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_forth.*

class ForthActivity : AppCompatActivity() {

    private var currentAdapter: gridCellAdapter? = null
    lateinit var gridArrayList: MutableList<ai>


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_forth)

        btnBack.setOnClickListener {
            val intent = Intent(this, BufferActivity::class.java)
            startActivity(intent)
        }

        gridArrayList = MutableList(adapterA.aiArrayList.size * adapterB.biArrayList.size) { ai() }
        currentAdapter = gridCellAdapter(this, gridArrayList)


        recycler.adapter = currentAdapter
        recycler.layoutManager = GridLayoutManager(
            applicationContext,
            adapterB.biArrayList.size
        )

    }
}
