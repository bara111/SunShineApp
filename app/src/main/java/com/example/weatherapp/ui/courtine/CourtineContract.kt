package com.example.weatherapp.ui.courtine

interface CourtineContract {
    interface Presenter {
        suspend fun fetchData() {
        }
    }

    interface View {
        fun updateUi(data: String)
    }
}