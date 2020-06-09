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



    }
}
