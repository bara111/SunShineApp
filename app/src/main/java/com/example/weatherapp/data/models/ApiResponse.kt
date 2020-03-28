package com.example.weatherapp.data.models

class ApiResponse<T : Any, E : String>(var Response: T?, var error: String?)
