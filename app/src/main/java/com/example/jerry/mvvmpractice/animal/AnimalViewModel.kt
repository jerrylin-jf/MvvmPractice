package com.example.jerry.mvvmpractice.animal

import android.databinding.ObservableField

class AnimalViewModel(val animal: Animal) {

    val info = ObservableField<String>("${animal.name} shout ${animal.shoutCount}")

    fun shoutFun() {
        animal.shoutCount++
        info.set("in shout fun ${animal.name} shout ${animal.shoutCount}")
    }
}