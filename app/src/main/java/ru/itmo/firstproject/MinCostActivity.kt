package ru.itmo.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_min_cost.*
class MinCostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_min_cost)
        supportActionBar?.hide()
        val a = adapterA.aiArrayList.size
        val b = adapterB.biArrayList.size
        val array = Array(a) { IntArray(b) }
        val plan = Array(a) { IntArray(b) }


/*        for (item in gridCellAdapter.gridArrayList) {
            Log.d("!!!!!!!!!!!!", "AAAAA   ${item.getAiValue().toInt()}")
            Log.d("111111111111", "AAAAA   ${item.getAiValue().toInt()}")
            array[i][k] = item.getAiValue().toInt()
            k++
            if (k >= b) {
                k = 0; i++
            }
        }*/
//        var i = 0
//        var k = 0
        for (i in 0 until a) {
            for (j in 0 until b) {
               array[i][j] = gridCellAdapter.gridArrayList[b*i+j].getAiValue().toInt()
            }
        }


        while (true) {
            var ai = 0
            var bi = 0
            var minElem = 1000
            for (i in 0 until a) {
                for (j in 0 until b) {
                    if (array[i][j] < minElem && array[i][j] != -1) {
                        minElem = array[i][j]; ai = i; bi = j
                    }
                }
            }
            if (minElem == 1000) {
                break
            }
            minElem = 0
            for(item in adapterB.biArrayList) {if (item.getAiValue().toInt() != -1) {minElem = -1}}
            if(minElem == 0) {break}
            if (adapterA.aiArrayList[ai].getAiValue().toInt() >= adapterB.biArrayList[bi].getAiValue().toInt() && adapterB.biArrayList[bi].getAiValue().toInt() != -1) {
                adapterA.aiArrayList[ai].setAiValue((adapterA.aiArrayList[ai].getAiValue().toInt() - adapterB.biArrayList[bi].getAiValue().toInt()).toString())
                plan[ai][bi] = adapterB.biArrayList[bi].getAiValue().toInt()
                adapterB.biArrayList[bi].setAiValue("-1")
                for (i in 0 until a) {
                    array[i][bi] = -1
                }
                if (adapterA.aiArrayList[ai].getAiValue().toInt() == 0) {
                    adapterA.aiArrayList[ai].setAiValue("-1"); for (i in 0 until b) {
                        array[ai][i] = -1
                    }
                }
            }
            if (adapterA.aiArrayList[ai].getAiValue().toInt() < adapterB.biArrayList[bi].getAiValue().toInt() && adapterA.aiArrayList[ai].getAiValue().toInt() != -1) {
                adapterB.biArrayList[bi].setAiValue((adapterB.biArrayList[bi].getAiValue().toInt() - adapterA.aiArrayList[ai].getAiValue().toInt()).toString())
                plan[ai][bi] = adapterA.aiArrayList[ai].getAiValue().toInt()
                adapterA.aiArrayList[ai].setAiValue("-1")
                for (i in 0 until b) {
                    array[ai][i] = -1
                }
            }
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

