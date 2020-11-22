package com.patch.bookinfoapp.data.repository

import com.patch.bookinfoapp.data.entity.BookData
import retrofit2.Response

interface BookDataStore {
    suspend fun getBook(query: String, page: Int, size: Int): BookData
}