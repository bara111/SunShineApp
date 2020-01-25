package com.example.weatherapp.extension

import android.content.Context
import android.view.View

val View.ctx: Context
    get() = this.context