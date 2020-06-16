package ru.itmo.firstproject

import android.content.Intent
import android.graphics.Color
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
        if(MainActivity.flag == 1) {
            gridArrayList = BufferActivity.createArrayFromInt(listOf(2, 4, 2, 3, 8, 3, 5, 6, 6, 2, 6, 8, 7, 4, 5, 3, 4, 2, 1, 4) as MutableList<Int>)//(listOf(7, 2, 4, 7, 2, 2, 2, 1, 10, 2, 4, 6) as MutableList<Int>)//
            recycler.layoutManager = GridLayoutManager(
                applicationContext,
                5 //3//
            )
        }
        else {
            gridArrayList =
                MutableList(adapterA.aiArrayList.size * adapterB.biArrayList.size) { ai() }
            recycler.layoutManager = GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )
        }
        currentAdapter = gridCellAdapter(this, gridArrayList)
        btnBack.setOnClickListener {
            var checker = 0
            for (item in gridArrayList) {
                if (!item.getAiValue().matches("[0-9]+".toRegex())) {
                    if (item.getAiValue() == "") {
                        item.setAiValue("0")
                    } else {
                        info.text = StringBuffer("Please enter only integer value")
                        info.setTextColor(Color.parseColor("#BD020E"))
                        checker = -1
                        break
                    }
                }
            }
            if (checker == 0) {
                val intent = Intent(this, BufferActivity::class.java)
                startActivity(intent)
            }
        }
        recycler.adapter = currentAdapter


    }
}
