package com.example.jerry.mvvmpractice.animal

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jerry.mvvmpractice.R
import com.example.jerry.mvvmpractice.databinding.ActivityAnimalBinding

class AnimalActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnimalBinding
    lateinit var viewModel: AnimalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_animal)

        val animal = Animal("pid", 0)
        viewModel = AnimalViewModel(animal)

        binding.vm = viewModel
    }
}