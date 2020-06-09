package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_south_east.*
class SouthEast : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_south_east)
        supportActionBar?.hide()
        val a = adapterA.aiArrayList.size
        val b = adapterB.biArrayList.size
        val plan = Array(a) { IntArray(b) }
        val aiArray = MutableList(0) {ai()}
        val biArray = MutableList(0) {ai()}
        for(item in adapterB.biArrayList)
        {
            val x = ai()
            x.setAiValue(item.getAiValue())
            biArray.add(x)
        }
        for(item in adapterA.aiArrayList)
        {
            val y = ai()
            y.setAiValue(item.getAiValue())
            aiArray.add(y)
        }
        //    val array = Array(a) { IntArray(b) }



/*        for (i in 0 until a) {
            for (j in 0 until b) {
                array[i][j] = gridCellAdapter.gridArrayList[b*i+j].getAiValue().toInt()
            }
        }*/

        var k = b-1
        var i = a-1
        while(true)
        {
            var buffer = 0
            for(item in biArray) {if(item.getAiValue().toInt() != -1) {buffer = -1}}
            if(buffer == 0) {break}
            if(aiArray[i].getAiValue().toInt() >= biArray[k].getAiValue().toInt() && biArray[k].getAiValue().toInt() != -1) {
                aiArray[i].setAiValue((aiArray[i].getAiValue().toInt() - biArray[k].getAiValue().toInt()).toString())
                plan[i][k] = biArray[k].getAiValue().toInt()
                biArray[k].setAiValue("-1")
                k--

                if (aiArray[i].getAiValue().toInt() == 0) {
                    aiArray[i].setAiValue("-1"); i--}
                continue
            }
            if(aiArray[i].getAiValue().toInt() < biArray[k].getAiValue().toInt() && biArray[k].getAiValue().toInt() != -1){
                biArray[k].setAiValue((biArray[k].getAiValue().toInt() - aiArray[i].getAiValue().toInt()).toString())
                plan[i][k] = aiArray[i].getAiValue().toInt()
                aiArray[i].setAiValue("-1") ; i--}

        }



        var finalcost = 0
        val res = MutableList(a*b) {""}
        for (h in 0 until a) {
            for (j in 0 until b) {
                if (plan[h][j] > 0) {
                    finalcost += plan[h][j]*gridCellAdapter.gridArrayList[b*h+j].getAiValue().toInt()
                    res[h*b+j] = plan[h][j].toString()
                }
            }
        }
        val currentAdapter: adapterGridTv?
        val gridTvArrayList: MutableList<ai> = MutableList(0) { ai()}
        for (item in res)
        {
            val aa = ai()
            aa.setAiValue(item)
            gridTvArrayList.add(aa)
        }
        currentAdapter = adapterGridTv(this, gridTvArrayList)
        gridrecycler.adapter = currentAdapter
        gridrecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                biArray.size
            )
        t.text = StringBuffer(t.text).append(finalcost)
       // t.text = "Transportation cost: $finalcost"
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