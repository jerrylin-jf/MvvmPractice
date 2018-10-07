package com.example.jerry.mvvmpractice.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import com.example.jerry.mvvmpractice.helper.Utils
import com.example.jerry.mvvmpractice.helper.async
import com.example.jerry.mvvmpractice.helper.set
import com.example.jerry.mvvmpractice.model.data.Article
import com.example.jerry.mvvmpractice.model.remote.PaoService
import com.example.jerry.mvvmpractice.model.respository.PaoRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PaoViewModel @Inject constructor(private val repo: PaoRepo) {

    val loading = MutableLiveData<Boolean>()
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()
    val error = MutableLiveData<Throwable>()

    fun loadArticle(): Single<Article> =
        repo.getArticleDetail(8773)
            .async(1000)
            .doOnSubscribe { startLoad() }
            .doAfterTerminate { stopLoad() }
            .doOnSuccess { article ->
                title.set(article.title)
                article.content?.let {
                    val articleContent = Utils.processImgSrc(it)
                    content.set(articleContent)
                }
            }

    private fun startLoad() = loading.set(true)
    private fun stopLoad() = loading.set(false)
}