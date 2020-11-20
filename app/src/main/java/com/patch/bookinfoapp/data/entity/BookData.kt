package com.patch.bookinfoapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class BookData(
    @SerializedName("meta")
    val metaData: Meta,
    val documents: List<Documents>
) {
    @Parcelize
    data class Documents (
        val title: String,
        val contents: String,
        @SerializedName("url")
        val detailUrl: String,
        val isbn: String,
        val datetime: String,
        val authors: List<String>,
        val publisher: String,
        val translators: List<String>,
        val price: Int,
        @SerializedName("sale_price")
        val salePrice: Int,
        val thumbnail: String,
        @SerializedName("status")
        val sellingStatus: String) : Parcelable
}