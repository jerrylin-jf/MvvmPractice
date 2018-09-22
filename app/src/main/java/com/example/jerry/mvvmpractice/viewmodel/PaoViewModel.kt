package com.example.jerry.mvvmpractice.viewmodel

import android.annotation.SuppressLint
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.jerry.mvvmpractice.helper.Utils
import com.example.jerry.mvvmpractice.helper.async
import com.example.jerry.mvvmpractice.model.data.Article
import com.example.jerry.mvvmpractice.model.remote.PaoService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PaoViewModel(val remote: PaoService) {

    val loading = ObservableBoolean(false)
    val title = ObservableField<String>()
    val content = ObservableField<String>()
    val error = ObservableField<Throwable>()

//    @SuppressLint("CheckResult")
//    fun loadArticle() {
//
//        remote.getArticleDetail(8773)
//            .async(1000)
//            .doOnSubscribe { startLoad() }
//            .doAfterTerminate { stopLoad() }
//            .subscribe({
//                title.set(it.title)
//                it.content?.let {
//                    val articleContent = Utils.processImgSrc(it)
//                    content.set(articleContent)
//                }
//            }, {
//
//            })
//    }

    fun loadArticle(): Single<Article> =
        remote.getArticleDetail(8773)
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


    fun startLoad() = loading.set(true)
    fun stopLoad() = loading.set(false)
}