package com.example.weatherapp.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class WeatherDailyData(
    val clouds: Clouds?,
    @SerializedName("dt")
    val unixValue: Int,
    @SerializedName("dt_txt")
    val time: String?,
    val main: Main?,
    val rain: Rain?,
    val sys: Sys?,
    val weather: List<Weather>?,
    val wind: Wind?

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Clouds::class.java.classLoader),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(Main::class.java.classLoader),
        parcel.readParcelable(Rain::class.java.classLoader),
        parcel.readParcelable(Sys::class.java.classLoader),
        parcel.createTypedArrayList(Weather),
        parcel.readParcelable(Wind::class.java.classLoader)
    )

    fun getFormatedTime(): String {
        return java.time.format.DateTimeFormatter.ISO_INSTANT
            .format(java.time.Instant.ofEpochSecond(unixValue.toLong()))
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(clouds, flags)
        parcel.writeInt(unixValue)
        parcel.writeString(time)
        parcel.writeParcelable(main, flags)
        parcel.writeParcelable(rain, flags)
        parcel.writeParcelable(sys, flags)
        parcel.writeTypedList(weather)
        parcel.writeParcelable(wind, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeatherDailyData> {
        override fun createFromParcel(parcel: Parcel): WeatherDailyData {
            return WeatherDailyData(parcel)
        }

        override fun newArray(size: Int): Array<WeatherDailyData?> {
            return arrayOfNulls(size)
        }
    }
}