package ru.itmo.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_north_west.*

class NorthWest : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_north_west)
        supportActionBar?.hide()
        val a = adapterA.aiArrayList.size
        val b = adapterB.biArrayList.size
    //    val array = Array(a) { IntArray(b) }
        val plan = Array(a) { IntArray(b) }


/*        for (i in 0 until a) {
            for (j in 0 until b) {
                array[i][j] = gridCellAdapter.gridArrayList[b*i+j].getAiValue().toInt()
            }
        }*/

var k = 0
var i = 0
while(true)
{
    var buffer = 0
    for(item in adapterB.biArrayList) {if(item.getAiValue().toInt() != -1) {buffer = -1}}
    if(buffer == 0) {break}
if(adapterA.aiArrayList[i].getAiValue().toInt() >= adapterB.biArrayList[k].getAiValue().toInt() && adapterB.biArrayList[k].getAiValue().toInt() != -1) {
    adapterA.aiArrayList[i].setAiValue((adapterA.aiArrayList[i].getAiValue().toInt() - adapterB.biArrayList[k].getAiValue().toInt()).toString())
    plan[i][k] = adapterB.biArrayList[k].getAiValue().toInt()
    adapterB.biArrayList[k].setAiValue("-1")
    k++

    if (adapterA.aiArrayList[i].getAiValue().toInt() == 0) {
        adapterA.aiArrayList[i].setAiValue("-1"); i++}
continue
}
if(adapterA.aiArrayList[i].getAiValue().toInt() < adapterB.biArrayList[k].getAiValue().toInt() && adapterB.biArrayList[k].getAiValue().toInt() != -1){
    adapterB.biArrayList[k].setAiValue((adapterB.biArrayList[k].getAiValue().toInt() - adapterA.aiArrayList[i].getAiValue().toInt()).toString())
    plan[i][k] = adapterA.aiArrayList[i].getAiValue().toInt()
    adapterA.aiArrayList[i].setAiValue("-1") ; i++}

}



        var finalcost = 0
        val res = MutableList(a*b) {x -> ""}
        for (i in 0 until a) {
            for (j in 0 until b) {
                if (plan[i][j] > 0) {
                    finalcost += plan[i][j]*gridCellAdapter.gridArrayList[b*i+j].getAiValue().toInt()
                    res[i*b+j] = plan[i][j].toString()
                    //         res[i*b+j] = array[i][j].toString()
                }
            }
        }
        var currentAdapter: adapterATV? = null // EditAdapter
        var ATVArrayList: MutableList<ai> = MutableList(0, { x -> ai()})
        for (item in res)
        {
            val aa = ai()
            aa.setAiValue(item)
            ATVArrayList.add(aa)
        }
        currentAdapter = adapterATV(this, ATVArrayList)
        gridrecycler.adapter = currentAdapter
        gridrecycler.layoutManager =
            GridLayoutManager(
                applicationContext,
                adapterB.biArrayList.size
            )
        t.setText("Transportation cost: $finalcost")





    }
}

