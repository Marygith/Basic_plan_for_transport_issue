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

        gridArrayList = MutableList(adapterA.aiArrayList.size * adapterB.biArrayList.size) { ai() }
        currentAdapter = gridCellAdapter(this, gridArrayList)


        recycler.adapter = currentAdapter
        recycler.layoutManager = GridLayoutManager(
            applicationContext,
            adapterB.biArrayList.size
        )

    }
}
