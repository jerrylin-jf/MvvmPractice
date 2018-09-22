package com.example.jerry.mvvmpractice.helper

import org.jsoup.Jsoup

object Utils {

    fun processImgSrc(content: String, baseUrl: String = Constants.HOST_PAO): String {

        val document = Jsoup.parse(content).apply {
            setBaseUri(baseUrl)
        }

        val element = document.select("img[src]")
        element.forEach { it ->
            val imgUrl = it.attr("src")
            if (imgUrl.trim { it <= ' ' }.startsWith("/")) {
                it.attr("src", it.absUrl("src"))
            }
        }
        return document.html()
    }
}