package com.example.jerry.mvvmpractice.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.jerry.mvvmpractice.R
import com.example.jerry.mvvmpractice.databinding.ActivityPaoBinding
import com.example.jerry.mvvmpractice.helper.bindLifeCycle
import com.example.jerry.mvvmpractice.viewmodel.PaoViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class PaoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPaoBinding
    @Inject lateinit var paoViewModel: PaoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pao)

        binding.vm = paoViewModel
        binding.setLifecycleOwner(this)

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