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
        val aiArray = adapterA.aiArrayList
        val biArray = adapterB.biArrayList
        val a = aiArray.size
        val b = biArray.size
        val array = Array(a) { IntArray(b) }
        val plan = Array(a) { IntArray(b) }

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
            for(item in biArray) {if (item.getAiValue().toInt() != -1) {minElem = -1}}
            if(minElem == 0) {break}
            if (aiArray[ai].getAiValue().toInt() >= biArray[bi].getAiValue().toInt() && biArray[bi].getAiValue().toInt() != -1) {
                aiArray[ai].setAiValue((aiArray[ai].getAiValue().toInt() - biArray[bi].getAiValue().toInt()).toString())
                plan[ai][bi] = biArray[bi].getAiValue().toInt()
                biArray[bi].setAiValue("-1")
                for (i in 0 until a) {
                    array[i][bi] = -1
                }
                if (aiArray[ai].getAiValue().toInt() == 0) {
                    aiArray[ai].setAiValue("-1"); for (i in 0 until b) {
                        array[ai][i] = -1
                    }
                }
            }
            if (aiArray[ai].getAiValue().toInt() < biArray[bi].getAiValue().toInt() && aiArray[ai].getAiValue().toInt() != -1) {
                biArray[bi].setAiValue((biArray[bi].getAiValue().toInt() - aiArray[ai].getAiValue().toInt()).toString())
                plan[ai][bi] = aiArray[ai].getAiValue().toInt()
                aiArray[ai].setAiValue("-1")
                for (i in 0 until b) {
                    array[ai][i] = -1
                }
            }
        }
var finalcost = 0
val res = MutableList(a*b) { ""}
        for (i in 0 until a) {
            for (j in 0 until b) {
                if (plan[i][j] > 0) {
                    finalcost += plan[i][j]*gridCellAdapter.gridArrayList[b*i+j].getAiValue().toInt()
                    res[i*b+j] = plan[i][j].toString()
       //         res[i*b+j] = array[i][j].toString()
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
        tablebtn.setOnClickListener {
            val intent = Intent(this, SourceDataActivity::class.java)
            startActivity(intent)
        }




    }
}

