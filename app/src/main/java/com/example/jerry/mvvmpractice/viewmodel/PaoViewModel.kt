package com.example.jerry.mvvmpractice.viewmodel

import android.annotation.SuppressLint
import android.databinding.ObservableField
import com.example.jerry.mvvmpractice.model.remote.PaoService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PaoViewModel(val remote: PaoService) {

    val articleDetail = ObservableField<String>()

    @SuppressLint("CheckResult")
    fun loadArticle() {

        remote.getArticleDetail(8773)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                articleDetail.set(it.toString())
            },{
                articleDetail.set(it.localizedMessage)
            })
    }
}