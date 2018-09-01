package com.example.jerry.mvvmpractice.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jerry.mvvmpractice.R
import com.example.jerry.mvvmpractice.databinding.ActivityPaoBinding
import com.example.jerry.mvvmpractice.model.remote.PaoService
import com.example.jerry.mvvmpractice.viewmodel.PaoViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class PaoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaoBinding
    lateinit var paoViewModel: PaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pao)

        val remote = Retrofit.Builder()
            .baseUrl("http://api.jcodecraeer.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaoService::class.java)

        paoViewModel = PaoViewModel(remote)
        binding.vm = paoViewModel

    }
}