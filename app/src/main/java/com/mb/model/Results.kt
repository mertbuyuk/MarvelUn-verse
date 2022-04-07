package com.mb.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


data class Results(
    val id : Int,
    val name : String,
    val description : String,
    val modified : String,
    val resourceURI : String,
    val thumbnail : Thumbnail,
    val urls : List<Urls>,
    val comics : Comics
)