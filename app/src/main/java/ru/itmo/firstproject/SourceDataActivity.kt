package ru.itmo.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_source_data.*
class SourceDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_source_data)
        supportActionBar?.hide()
        val adapterGrid: adapterGridTv?

        adapterGrid = adapterGridTv(this, gridCellAdapter.gridArrayList)
        gridrecycler.adapter = adapterGrid
        gridrecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )


        val adapterAi = adapterATv(this, adapterA.aiArrayList)
        arecycler.adapter = adapterAi
        arecycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        val adapterBi = adapterBTv(this, adapterB.biArrayList)
        brecycler.adapter = adapterBi
        brecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )
    }
}
