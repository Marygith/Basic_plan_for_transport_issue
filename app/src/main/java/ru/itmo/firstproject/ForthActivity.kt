package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_forth.*
class ForthActivity : AppCompatActivity() {

    private var currentAdapter: gridCellAdapter? = null // EditAdapter
    lateinit var gridArrayList: MutableList<ai> // EditModel  EditModelArrayList


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
       // requestWindowFeature(Window.FEATURE_NO_TITLE);   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_forth)

        btnNext.setOnClickListener {
            val intent = Intent(this, MinCostActivity::class.java)
            startActivity(intent)
        }

        gridArrayList= MutableList(adapterA.aiArrayList.size*adapterB.biArrayList.size) { ai()}
        currentAdapter = gridCellAdapter(this, gridArrayList)
        // aiArrayList = MutableList(1) { x -> ai() }


        recycler.adapter = currentAdapter
/*        btn.setOnClickListener {
            gridArrayList.add(ai())
            currentAdapter!!.notifyDataSetChanged()

        }*/
        recycler.layoutManager = GridLayoutManager(
            applicationContext,
            adapterB.biArrayList.size
        )
//recycler.layoutManager = GridLayoutManager(applicationContext, GridLayoutManager.HORIZONTAL)
    }
}
