package com.example.jerry.mvvmpractice.model.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.jerry.mvvmpractice.model.data.Article
import io.reactivex.Single

@Dao
interface PaoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetAll(articles: List<Article>)

    @Query("SELECT * FROM Articles WHERE articleid= :id")
    fun getArticleById(id: Int): Single<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: Article)
}