package com.example.restik_beta

import android.app.Application

class MyApp : Application() {

    lateinit var mainViewModel: MainViewModel

    lateinit var listViewModel: ListViewModel


    override fun onCreate() {
        super.onCreate()

        val repository = Repository()

        mainViewModel = MainViewModel(repository)

        listViewModel = ListViewModel(repository)

    }
}