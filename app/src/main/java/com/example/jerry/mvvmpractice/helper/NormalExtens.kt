package com.example.jerry.mvvmpractice.helper

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.SingleSubscribeProxy
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import us.feras.mdv.MarkdownView
import java.util.concurrent.TimeUnit

fun MarkdownView.setMarkdown(markdown: String?) {
    loadMarkdown(markdown, "file:///android_asset/markdown.css")
}

fun <T> Single<T>.async(withDelay: Long = 0): Single<T> =
    this.subscribeOn(Schedulers.io())
        .delay(withDelay, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.bindLifeCycle(owner: LifecycleOwner): SingleSubscribeProxy<T> {
    return this.`as`(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(owner)))
}

fun <T : Any> MutableLiveData<T>.set(value: T?) = postValue(value)

fun <T : Any> MutableLiveData<T>.get() = value