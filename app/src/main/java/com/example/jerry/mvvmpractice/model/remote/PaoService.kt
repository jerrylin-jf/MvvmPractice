package com.example.jerry.mvvmpractice.model.remote

import com.example.jerry.mvvmpractice.model.data.Article
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PaoService {
    @GET("article_detail.php")
    fun getArticleDetail(@Query("id") id: Int): Single<Article>
}