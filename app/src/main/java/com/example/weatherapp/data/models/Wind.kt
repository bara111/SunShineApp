package com.example.weatherapp.data.models

import android.os.Parcel
import android.os.Parcelable

data class Wind(
    val deg: Int,
    val speed: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readDouble()
    )

    fun getSpeedWithUnit(): String {
        return this.speed.toString() + " m/s"
    }

    fun getDegreeWithUnit(): String {
        return this.deg.toString() + "°"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(deg)
        parcel.writeDouble(speed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wind> {
        override fun createFromParcel(parcel: Parcel): Wind {
            return Wind(parcel)
        }

        override fun newArray(size: Int): Array<Wind?> {
            return arrayOfNulls(size)
        }
    }
}