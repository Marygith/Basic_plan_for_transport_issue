package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_buffer.*

class BufferActivity : AppCompatActivity() {

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
        var aiArray = MutableList(0) { ai() }
        var biArray = MutableList(0) { ai() }
        for (item in adapterB.biArrayList) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            biArray.add(x)
        }
        for (item in adapterA.aiArrayList) {
            val y = ai()
            y.setAiValue(item.getAiValue())
            aiArray.add(y)


        }
        var planMC = Array(a) { IntArray(b) }
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
        var res = MutableList(a * b) { "" }
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



        aiArray.clear()
        biArray.clear()
        aiArray = MutableList(0) { ai() }
        biArray = MutableList(0) { ai() }
        for (item in adapterB.biArrayList) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            biArray.add(x)
        }
        for (item in adapterA.aiArrayList) {
            val y = ai()
            y.setAiValue(item.getAiValue())
            aiArray.add(y)
        }
        var planNW = Array(a) { IntArray(b) }
        var k = 0
        var i = 0
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
                planNW[i][k] = biArray[k].getAiValue().toInt()
                biArray[k].setAiValue("-1")
                k++

                if (aiArray[i].getAiValue().toInt() == 0) {
                    aiArray[i].setAiValue("-1"); i++
                }
                continue
            }
            if (aiArray[i].getAiValue().toInt() < biArray[k].getAiValue()
                    .toInt() && biArray[k].getAiValue().toInt() != -1
            ) {
                biArray[k].setAiValue(
                    (biArray[k].getAiValue().toInt() - aiArray[i].getAiValue().toInt()).toString()
                )
                planNW[i][k] = aiArray[i].getAiValue().toInt()
                aiArray[i].setAiValue("-1"); i++
            }

        }
        costNW = 0
        res.clear()
        res = MutableList(a * b) { "" }
        for (h in 0 until a) {
            for (j in 0 until b) {
                if (planNW[h][j] > 0) {
                    costNW += planNW[h][j] * gridCellAdapter.gridArrayList[b * h + j].getAiValue()
                        .toInt()
                    res[h * b + j] = planNW[h][j].toString()
                }
            }
        }
        arrayNW.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            arrayNW.add(aa)
        }



        aiArray.clear()
        biArray.clear()
        aiArray = MutableList(0) { ai() }
        biArray = MutableList(0) { ai() }
        for (item in adapterB.biArrayList) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            biArray.add(x)
        }
        for (item in adapterA.aiArrayList) {
            val y = ai()
            y.setAiValue(item.getAiValue())
            aiArray.add(y)
        }
        var planNE = Array(a) { IntArray(b) }
        k = b - 1
        i = 0
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
                planNE[i][k] = biArray[k].getAiValue().toInt()
                biArray[k].setAiValue("-1")
                k--

                if (aiArray[i].getAiValue().toInt() == 0) {
                    aiArray[i].setAiValue("-1"); i++
                }
                continue
            }
            if (aiArray[i].getAiValue().toInt() < biArray[k].getAiValue()
                    .toInt() && biArray[k].getAiValue().toInt() != -1
            ) {
                biArray[k].setAiValue(
                    (biArray[k].getAiValue().toInt() - aiArray[i].getAiValue().toInt()).toString()
                )
                planNE[i][k] = aiArray[i].getAiValue().toInt()
                aiArray[i].setAiValue("-1"); i++
            }
        }
        costNE = 0
        res.clear()
        res = MutableList(a * b) { "" }
        for (h in 0 until a) {
            for (j in 0 until b) {
                if (planNE[h][j] > 0) {
                    costNE += planNE[h][j] * gridCellAdapter.gridArrayList[b * h + j].getAiValue()
                        .toInt()
                    res[h * b + j] = planNE[h][j].toString()
                }
            }
        }
        arrayNE.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            arrayNE.add(aa)
        }


        aiArray.clear()
        biArray.clear()
        aiArray = MutableList(0) { ai() }
        biArray = MutableList(0) { ai() }
        for (item in adapterB.biArrayList) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            biArray.add(x)
        }
        for (item in adapterA.aiArrayList) {
            val y = ai()
            y.setAiValue(item.getAiValue())
            aiArray.add(y)
        }
        var planSW = Array(a) { IntArray(b) }
        k = 0
        i = a - 1
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
                planSW[i][k] = biArray[k].getAiValue().toInt()
                biArray[k].setAiValue("-1")
                k++

                if (aiArray[i].getAiValue().toInt() == 0) {
                    aiArray[i].setAiValue("-1"); i--
                }
                continue
            }
            if (aiArray[i].getAiValue().toInt() < biArray[k].getAiValue()
                    .toInt() && biArray[k].getAiValue().toInt() != -1
            ) {
                biArray[k].setAiValue(
                    (biArray[k].getAiValue().toInt() - aiArray[i].getAiValue().toInt()).toString()
                )
                planSW[i][k] = aiArray[i].getAiValue().toInt()
                aiArray[i].setAiValue("-1"); i--
            }
        }
        costSW = 0
        res.clear()
        res = MutableList(a * b) { "" }
        for (h in 0 until a) {
            for (j in 0 until b) {
                if (planSW[h][j] > 0) {
                    costSW += planSW[h][j] * gridCellAdapter.gridArrayList[b * h + j].getAiValue()
                        .toInt()
                    res[h * b + j] = planSW[h][j].toString()
                }
            }
        }
        arraySW.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            arraySW.add(aa)
        }
        aiArray.clear()
        biArray.clear()
        aiArray = MutableList(0) { ai() }
        biArray = MutableList(0) { ai() }
        for (item in adapterB.biArrayList) {
            val x = ai()
            x.setAiValue(item.getAiValue())
            biArray.add(x)
        }
        for (item in adapterA.aiArrayList) {
            val y = ai()
            y.setAiValue(item.getAiValue())
            aiArray.add(y)
        }
        var planSE = Array(a) { IntArray(b) }
        k = b - 1
        i = a - 1
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
                planSE[i][k] = biArray[k].getAiValue().toInt()
                biArray[k].setAiValue("-1")
                k--

                if (aiArray[i].getAiValue().toInt() == 0) {
                    aiArray[i].setAiValue("-1"); i--
                }
                continue
            }
            if (aiArray[i].getAiValue().toInt() < biArray[k].getAiValue()
                    .toInt() && biArray[k].getAiValue().toInt() != -1
            ) {
                biArray[k].setAiValue(
                    (biArray[k].getAiValue().toInt() - aiArray[i].getAiValue().toInt()).toString()
                )
                planSE[i][k] = aiArray[i].getAiValue().toInt()
                aiArray[i].setAiValue("-1"); i--
            }

        }
        costSE = 0
        res.clear()
        res = MutableList(a * b) { "" }
        for (h in 0 until a) {
            for (j in 0 until b) {
                if (planSE[h][j] > 0) {
                    costSE += planSE[h][j] * gridCellAdapter.gridArrayList[b * h + j].getAiValue()
                        .toInt()
                    res[h * b + j] = planSE[h][j].toString()
                }
            }
        }
        arraySE.clear()
        for (item in res) {
            val aa = ai()
            aa.setAiValue(item)
            arraySE.add(aa)
        }

        // cost.text =  StringBuffer("costMC: $costMC costNW: $costNW  costSW: $costSW costSE: $costSE")

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
