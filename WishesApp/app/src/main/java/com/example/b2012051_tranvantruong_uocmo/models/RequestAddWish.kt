package com.example.b2012051_tranvantruong_uocmo.models

import com.google.gson.annotations.SerializedName

data class RequestAddWish(
    val idUser: String,
    @SerializedName("name")
    val fullName: String,
    val content: String
)
