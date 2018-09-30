package com.example.jerry.mvvmpractice.model.respository

import com.example.jerry.mvvmpractice.model.local.dao.PaoDao
import com.example.jerry.mvvmpractice.model.remote.PaoService

class PaoRepo constructor(
    private val remote: PaoService,
    private val local: PaoDao
) {
    fun getArticleDetail(id: Int) =
//        local.getArticleById(id)
//            .onErrorResumeNext {
                remote.getArticleDetail(id)
                    .doOnSuccess { local.insertArticle(it) }
//            }
}