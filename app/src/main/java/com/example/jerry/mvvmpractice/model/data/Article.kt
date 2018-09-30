package com.example.jerry.mvvmpractice.model.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "articles")
data class Article(

    @PrimaryKey
    @ColumnInfo(name = "articleid")
    var id: Int = 0,
    var title: String?,
    var readme: String?,
    @SerializedName("describe")
    var describe: String?,
    var click: Int = 0,
    var channel: Int = 0,
    var comments: Int = 0,
    var stow: Int = 0,
    var upvote: Int = 0,
    var downvote: Int = 0,
    var url: String?,
    var pubDate: String?,
    var thumbnail: String?
) {
    var content: String? = null
}