package com.example.jerry.mvvmpractice.helper

import android.arch.lifecycle.LifecycleOwner
import com.uber.autodispose.SingleSubscribeProxy
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import us.feras.mdv.MarkdownView
import java.util.concurrent.TimeUnit

fun MarkdownView.setMarkdown(markdownView: String?) {
    loadMarkdown(markdownView, "file:///android_asset/markdown.css")
}

fun <T> Single<T>.async(withDelay: Long = 0): Single<T> =
    this.subscribeOn(Schedulers.io())
        .delay(withDelay, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
