package com.example.weatherapp.data.models

class ApiResponse<T : Any, E : Throwable>(var Response: T?, var error: Throwable?)
