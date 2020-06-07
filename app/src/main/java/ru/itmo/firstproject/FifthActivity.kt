package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_fifth.*
class FifthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
        supportActionBar?.hide()
        val adapterGrid: adapterATV?
       val adapterAi : adapterATV
        val adapterBi : adapterATV
        var ATVArrayList: MutableList<ai> = MutableList(0, { x -> ai()})
        val aArrayList: MutableList<ai> = MutableList(0, { x -> ai()})
        val bArrayList: MutableList<ai> = MutableList(0, { x -> ai()})
/*        for (item in adapterATV.ATVArrayList)
        {
            val a = ai()
            a.setAiValue(item.getAiValue())
            ATVArrayList.add(a)
        }*/
        adapterGrid = adapterATV(this, gridCellAdapter.gridArrayList)
        gridrecycler.adapter = adapterGrid
        gridrecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )
for(item in adapterA.aiArrayList)
{
    val a = ai()
    a.setAiValue(item.getAiValue())
    aArrayList.add(a)
}
        for(item in adapterB.biArrayList)
        {
            val a = ai()
            a.setAiValue(item.getAiValue())
            bArrayList.add(a)
        }

        adapterAi = adapterATV(this, aArrayList)
        arecycler.adapter = adapterAi
        arecycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
        adapterBi = adapterATV(this, bArrayList)
        brecycler.adapter = adapterBi
        brecycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.HORIZONTAL,
                false
            )
    }
}
