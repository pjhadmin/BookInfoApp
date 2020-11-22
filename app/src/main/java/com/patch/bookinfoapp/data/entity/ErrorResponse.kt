package com.patch.bookinfoapp.data.entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorType") val errorType: String? = "",
    @SerializedName("message") val message: String? = ""
)