package ru.itmo.firstproject

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_buffer.*

class BufferActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
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

        costMC = minCostAlgorithm(adapterA.aiArrayList, adapterB.biArrayList, gridCellAdapter.gridArrayList, arrayMC)
        costNW = cornerAlgorithm(1, 1, 0, 0,  arrayNW, adapterA.aiArrayList, adapterB.biArrayList, gridCellAdapter.gridArrayList)
        costNE = cornerAlgorithm(1, -1, adapterB.biArrayList.size-1, 0, arrayNE, adapterA.aiArrayList, adapterB.biArrayList, gridCellAdapter.gridArrayList)
        costSE = cornerAlgorithm(-1, -1, adapterB.biArrayList.size-1, adapterA.aiArrayList.size-1, arraySE, adapterA.aiArrayList, adapterB.biArrayList, gridCellAdapter.gridArrayList)
        costSW = cornerAlgorithm(-1, 1, 0, adapterA.aiArrayList.size-1, arraySW, adapterA.aiArrayList, adapterB.biArrayList, gridCellAdapter.gridArrayList)

        //Statistics
        val costPercentArray = arrayOf(0, 0, 0, 0, 0)
        for(i in 1..100) {
            val arrayA = MutableList(4) { (1..100).random() }
            val sumA = arrayA.sum()
            val arrayB = MutableList(1) { (1..100).random() }
            while (sumA > arrayB.sum()) {
                arrayB.add((1..100).random())
            }
            arrayB.removeAt(arrayB.size - 1)
            arrayB.add(sumA - arrayB.sum())
            val arrayGrid = MutableList(arrayA.size * arrayB.size) { (1..10).random() }
            val mc = minCostAlgorithm(createArrayFromInt(arrayA), createArrayFromInt(arrayB), createArrayFromInt(arrayGrid), MutableList(0){ai()})
            val nw = cornerAlgorithm(1, 1, 0, 0,   MutableList(0){ai()}, createArrayFromInt(arrayA), createArrayFromInt(arrayB), createArrayFromInt(arrayGrid))
            val ne = cornerAlgorithm(1, -1, arrayB.size-1, 0,   MutableList(0){ai()}, createArrayFromInt(arrayA), createArrayFromInt(arrayB), createArrayFromInt(arrayGrid))
            val sw = cornerAlgorithm(-1, 1, 0, arrayA.size-1,   MutableList(0){ai()}, createArrayFromInt(arrayA), createArrayFromInt(arrayB), createArrayFromInt(arrayGrid))
            val se = cornerAlgorithm(-1, -1, arrayB.size-1, arrayA.size-1,   MutableList(0){ai()}, createArrayFromInt(arrayA), createArrayFromInt(arrayB), createArrayFromInt(arrayGrid))

            val costArray = listOf(mc, nw, ne, sw, se)
            for (q in 0..4) {
                if (costArray[q] == costArray.min()) {
                    costPercentArray[q]++
                }
            }
        }


        setColorAndText(costMC, btnCostMC, costPercentArray[0]) // 94
        setColorAndText(costNW, btnCostNW, costPercentArray[1]) // 3
        setColorAndText(costNE, btnCostNE, costPercentArray[2]) // 3
        setColorAndText(costSW, btnCostSW, costPercentArray[3]) // 3
        setColorAndText(costSE, btnCostSE, costPercentArray[4]) // 3

        btn_start.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun setColorAndText(cost: Int, tv : TextView, percent : Int) {
        val costArray = listOf(costMC, costSE, costSW, costNW, costNE)
        if(cost == costArray.max()) {tv.setTextColor(Color.parseColor("#BD020E"))}
        if(cost == costArray.min()) {tv.setTextColor(Color.parseColor("#027106"))}
        tv.text = StringBuffer("Final cost: $cost ($percent%)")
    }
    private fun createArray(array : MutableList<ai>) : MutableList<ai> {
        val arr = MutableList(0) { ai() }
        for (item in array) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            arr.add(x)
        }
        return arr
    }


    private fun cornerAlgorithm(x : Int, y : Int, pos1 : Int, pos2 : Int, array : MutableList<ai>, arrayA : MutableList<ai>, arrayB : MutableList<ai>, arrayGrid : MutableList<ai>) : Int {
        val a = arrayA.size
        val b = arrayB.size
        val aiArray = createArray(arrayA)
        val biArray = createArray(arrayB)
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
                    cost += plan[h][j] * arrayGrid[b * h + j].getAiValue()
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
    private fun minCostAlgorithm(arrayA : MutableList<ai>, arrayB : MutableList<ai>, arrayGrid : MutableList<ai>, arrayMinCost : MutableList<ai>) : Int {
        val a = arrayA.size
        val b = arrayB.size
        val aiArray = createArray(arrayA)
        val biArray = createArray(arrayB)
        val planMC = Array(a) { IntArray(b) }
        val array = Array(a) { IntArray(b) }

        for (i in 0 until a) {
            for (j in 0 until b) {
                array[i][j] = arrayGrid[b * i + j].getAiValue().toInt()
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
        var cost = 0
        val res = MutableList(a * b) { "" }
        for (i in 0 until a) {
            for (j in 0 until b) {
                if (planMC[i][j] > 0) {
                    cost += planMC[i][j] * arrayGrid[b * i + j].getAiValue()
                        .toInt()
                    res[i * b + j] = planMC[i][j].toString()
                }
            }
        }
        arrayMinCost.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            arrayMinCost.add(aa)
        }
        return cost
    }
    companion object {
         fun createArrayFromInt(array : MutableList<Int>) : MutableList<ai> {
            val arr = MutableList(0) { ai() }
            for (item in array) {
                val x = ai()
                x.setAiValue(item.toString())
                arr.add(x)
            }
            return arr
        }
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
