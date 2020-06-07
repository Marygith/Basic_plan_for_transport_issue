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
        lateinit var ATVArrayList: MutableList<ai>
      var currentAdapter: adapterATV? = null // EditAdapter
        ATVArrayList = MutableList(0, {x -> ai()})
        for (item in adapterA.aiArrayList)
        {
            val a = ai()
            a.setAiValue(item.getAiValue())
            ATVArrayList.add(a)
        }
        currentAdapter = adapterATV(this, ATVArrayList)
        arecycler.adapter = currentAdapter
        arecycler.layoutManager =
            LinearLayoutManager(
                applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )


    }
}
