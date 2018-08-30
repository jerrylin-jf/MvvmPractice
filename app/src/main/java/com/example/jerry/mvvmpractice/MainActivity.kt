package com.example.jerry.mvvmpractice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jerry.mvvmpractice.animal.AnimalActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener { startActivity(Intent(this, AnimalActivity::class.java)) }
    }
}
