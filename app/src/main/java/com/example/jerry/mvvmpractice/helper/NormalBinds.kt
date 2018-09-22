package com.example.jerry.mvvmpractice.helper

import android.databinding.BindingAdapter
import android.view.View
import android.widget.Toast
import us.feras.mdv.MarkdownView

@BindingAdapter(value = "markdown")
fun bindMarkDown(v: MarkdownView, markdown: String?) {
    markdown?.let {
        v.setMarkdown(it)
    }
}

@BindingAdapter(value = "toast")
fun bindToast(v: View, msg: Throwable ?) {
    msg?.let {
        Toast.makeText(v.context, it.message, 1).show()
    }
}