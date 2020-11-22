package com.patch.bookinfoapp.data.api

import com.patch.bookinfoapp.data.entity.BookData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteBookApi {

    /**
     *  책 검색 BOOK API
     */

    @GET("/v3/search/book")
    suspend fun getBookSearch(
        @Query("query") query: String,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int = 1,
        @Query("size") size: Int? = null,
        @Query("target") target: String? = null
    ): BookData
}