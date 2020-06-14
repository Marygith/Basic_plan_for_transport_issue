package ru.itmo.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sample.setOnClickListener {
            flag = 1
            openSecondActivity()
        }
        start.setOnClickListener {
            flag = 0
            openSecondActivity()
        }

    }
    private fun openSecondActivity() {

val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)

    }
    companion object {var flag = 1}
}
