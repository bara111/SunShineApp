package com.example.weatherapp.ui.courtine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class CourtineActivityPresenter @Inject constructor() : CourtineContract.Presenter {
    private lateinit var dataResult: String
    private lateinit var view: CourtineContract.View
    fun setView(view: CourtineContract.View) {
        this.view = view
    }

    override suspend fun fetchData() {
        withContext(Dispatchers.IO) {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"  // optional default is GET
                inputStream.bufferedReader().use {
                    it.lines().forEach { line ->
                        println(line)
                        dataResult = line
                    }
                }
            }
        }
        this.view.updateUi(dataResult)
    }

    companion object {
        val url =
            URL("https://api.openweathermap.org/data/2.5/forecast?id=524901&appid=829740af696dc0258609d2d0a6a8472a")
    }

}
