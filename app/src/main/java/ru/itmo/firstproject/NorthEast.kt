package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_north_east.*


class NorthEast : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_north_east)
        supportActionBar?.hide()

        val currentAdapter: adapterGridTv?
        currentAdapter = adapterGridTv(this, BufferActivity.arrayNE)

        gridrecycler.adapter = currentAdapter
        gridrecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )
        t.text = StringBuffer("Transportation cost: ${BufferActivity.costNE}")

        tablebtn.setOnClickListener {
            val intent = Intent(this, SourceDataActivity::class.java)
            startActivity(intent)
        }
        btnBack.setOnClickListener {
            val intent = Intent(this, BufferActivity::class.java)
            startActivity(intent)
        }
    }
}

