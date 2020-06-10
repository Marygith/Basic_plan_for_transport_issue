package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_min_cost.*
class MinCostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_min_cost)
        supportActionBar?.hide()

        val currentAdapter: adapterGridTv?

        currentAdapter = adapterGridTv(this, BufferActivity.arrayMC)
        gridrecycler.adapter = currentAdapter
        gridrecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )

        t.text = StringBuffer("Transportation cost: ${BufferActivity.costMC}")
        tablebtn.setOnClickListener {
            val intent = Intent(this, SourceDataActivity::class.java)
            startActivity(intent)
        }
    }
}

