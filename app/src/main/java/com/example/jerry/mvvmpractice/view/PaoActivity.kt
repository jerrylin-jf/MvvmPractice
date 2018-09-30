package com.example.jerry.mvvmpractice.view

import com.example.jerry.mvvmpractice.R
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.jerry.mvvmpractice.databinding.ActivityPaoBinding
import com.example.jerry.mvvmpractice.helper.bindLifeCycle
import com.example.jerry.mvvmpractice.model.local.AppDatabase
import com.example.jerry.mvvmpractice.model.remote.PaoService
import com.example.jerry.mvvmpractice.model.respository.PaoRepo
import com.example.jerry.mvvmpractice.viewmodel.PaoViewModel
import kotlinx.android.synthetic.main.activity_pao.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .build()
            )
            .build()
            .create(PaoService::class.java)

        val local = AppDatabase.getInstance(applicationContext).paoDao()
        val repo = PaoRepo(remote, local)
        paoViewModel = PaoViewModel(repo)
        binding.vm = paoViewModel

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.let {
            menuInflater.inflate(R.menu.detail_menu, it)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.action_refresh -> paoViewModel.loadArticle()
                    .bindLifeCycle(this)
                    .subscribe({}, { dispatchError(it) })
                else -> {
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dispatchError(error: Throwable?, length: Int = Toast.LENGTH_SHORT) {
        error?.let {
            it.printStackTrace()
            Toast.makeText(this, it.message, length).show()
        }
    }
}