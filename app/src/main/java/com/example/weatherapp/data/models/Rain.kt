package com.example.weatherapp.data.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("`3h`")
    val snowValue: Double
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readDouble())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(snowValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Rain> {
        override fun createFromParcel(parcel: Parcel): Rain {
            return Rain(parcel)
        }

        override fun newArray(size: Int): Array<Rain?> {
            return arrayOfNulls(size)
        }
    }
}