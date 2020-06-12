package ru.itmo.firstproject

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_buffer.*

class BufferActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer)
        buttonNE.setOnClickListener {
            val intent = Intent(this, NorthEast::class.java)
            startActivity(intent)
        }

        buttonNW.setOnClickListener {
            val intent = Intent(this, NorthWest::class.java)
            startActivity(intent)
        }

        buttonSW.setOnClickListener {
            val intent = Intent(this, SouthWest::class.java)
            startActivity(intent)
        }

        buttonSE.setOnClickListener {
            val intent = Intent(this, SouthEast::class.java)
            startActivity(intent)
        }

        buttonMC.setOnClickListener {
            val intent = Intent(this, MinCostActivity::class.java)
            startActivity(intent)
        }
        val a = adapterA.aiArrayList.size
        val b = adapterB.biArrayList.size
        val aiArray = createArray(adapterA.aiArrayList)
        val biArray = createArray(adapterB.biArrayList)
        val planMC = Array(a) { IntArray(b) }
        val array = Array(a) { IntArray(b) }

        for (i in 0 until a) {
            for (j in 0 until b) {
                array[i][j] = gridCellAdapter.gridArrayList[b * i + j].getAiValue().toInt()
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
            for (item in biArray) {
                if (item.getAiValue().toInt() != -1) {
                    minElem = -1
                }
            }
            if (minElem == 0) {
                break
            }
            if (aiArray[ai].getAiValue().toInt() >= biArray[bi].getAiValue()
                    .toInt() && biArray[bi].getAiValue().toInt() != -1
            ) {
                aiArray[ai].setAiValue(
                    (aiArray[ai].getAiValue().toInt() - biArray[bi].getAiValue().toInt()).toString()
                )
                planMC[ai][bi] = biArray[bi].getAiValue().toInt()
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
            if (aiArray[ai].getAiValue().toInt() < biArray[bi].getAiValue()
                    .toInt() && aiArray[ai].getAiValue().toInt() != -1
            ) {
                biArray[bi].setAiValue(
                    (biArray[bi].getAiValue().toInt() - aiArray[ai].getAiValue().toInt()).toString()
                )
                planMC[ai][bi] = aiArray[ai].getAiValue().toInt()
                aiArray[ai].setAiValue("-1")
                for (i in 0 until b) {
                    array[ai][i] = -1
                }
            }
        }
        costMC = 0
        val res = MutableList(a * b) { "" }
        for (i in 0 until a) {
            for (j in 0 until b) {
                if (planMC[i][j] > 0) {
                    costMC += planMC[i][j] * gridCellAdapter.gridArrayList[b * i + j].getAiValue()
                        .toInt()
                    res[i * b + j] = planMC[i][j].toString()
                }
            }
        }
        arrayMC.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            arrayMC.add(aa)
        }

        costNW = cornerAlgorithm(1, 1, 0, 0,  arrayNW)
        costNE = cornerAlgorithm(1, -1, b-1, 0, arrayNE)
        costSE = cornerAlgorithm(-1, -1, b-1, a-1, arraySE)
        costSW = cornerAlgorithm(-1, 1, 0, a-1, arraySW)

        setColorAndText(costMC, btnCostMC)
        setColorAndText(costNW, btnCostNW)
        setColorAndText(costNE, btnCostNE)
        setColorAndText(costSW, btnCostSW)
        setColorAndText(costSE, btnCostSE)




    }
    fun setColorAndText(cost: Int, tv : TextView) {
        val costArray = listOf(costMC, costSE, costSW, costNW, costNE)
        if(cost == costArray.max()) {tv.setTextColor(Color.parseColor("#BD020E"))}
        if(cost == costArray.min()) {tv.setTextColor(Color.parseColor("#027106"))}
        tv.text = StringBuffer("Final cost: $cost")
    }
    private fun createArray(array : MutableList<ai>) : MutableList<ai> {
        val Array = MutableList(0) { ai() }
        for (item in array) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            Array.add(x)
        }
        return Array
    }

    private fun cornerAlgorithm(x : Int, y : Int, pos1 : Int, pos2 : Int, array : MutableList<ai>) : Int {
        val a = adapterA.aiArrayList.size
        val b = adapterB.biArrayList.size
        val aiArray = createArray(adapterA.aiArrayList)
        val biArray = createArray(adapterB.biArrayList)
        val plan = Array(a) { IntArray(b) }
        var k = pos1
        var i = pos2
        while (true) {
            var buffer = 0
            for (item in biArray) {
                if (item.getAiValue().toInt() != -1) {
                    buffer = -1
                }
            }
            if (buffer == 0) {
                break
            }
            if (aiArray[i].getAiValue().toInt() >= biArray[k].getAiValue()
                    .toInt() && biArray[k].getAiValue().toInt() != -1
            ) {
                aiArray[i].setAiValue(
                    (aiArray[i].getAiValue().toInt() - biArray[k].getAiValue().toInt()).toString()
                )
                plan[i][k] = biArray[k].getAiValue().toInt()
                biArray[k].setAiValue("-1")
                k+=y

                if (aiArray[i].getAiValue().toInt() == 0) {
                    aiArray[i].setAiValue("-1"); i+=x
                }
                continue
            }
            if (aiArray[i].getAiValue().toInt() < biArray[k].getAiValue()
                    .toInt() && biArray[k].getAiValue().toInt() != -1
            ) {
                biArray[k].setAiValue(
                    (biArray[k].getAiValue().toInt() - aiArray[i].getAiValue().toInt()).toString()
                )
                plan[i][k] = aiArray[i].getAiValue().toInt()
                aiArray[i].setAiValue("-1"); i+=x
            }

        }
        var cost = 0
        val res = MutableList(a * b) { "" }
        for (h in 0 until a) {
            for (j in 0 until b) {
                if (plan[h][j] > 0) {
                    cost += plan[h][j] * gridCellAdapter.gridArrayList[b * h + j].getAiValue()
                        .toInt()
                    res[h * b + j] = plan[h][j].toString()
                }
            }
        }
        array.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            array.add(aa)
        }
        return cost
    }
    companion object {
        var costMC = 0
        var costNW = 0
        var costNE = 0
        var costSW = 0
        var costSE = 0
        val arraySW: MutableList<ai> = MutableList(0) { ai() }
        val arraySE: MutableList<ai> = MutableList(0) { ai() }
        val arrayMC: MutableList<ai> = MutableList(0) { ai() }
        val arrayNW: MutableList<ai> = MutableList(0) { ai() }
        val arrayNE: MutableList<ai> = MutableList(0) { ai() }
    }
}
