package com.patch.bookinfoapp.domain.repository

import com.patch.bookinfoapp.data.entity.BookData
import retrofit2.Response

interface BookRepository {
    companion object {
        const val DEFAULT_PAGE_SIZE = 50
    }

    suspend fun getBook(query: String, page: Int = 1, size: Int = DEFAULT_PAGE_SIZE): Response<BookData>
}