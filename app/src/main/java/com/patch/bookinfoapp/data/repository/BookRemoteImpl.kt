package com.patch.bookinfoapp.data.repository

import com.patch.bookinfoapp.data.api.RemoteBookApi
import com.patch.bookinfoapp.data.entity.BookData
import retrofit2.Response
import javax.inject.Inject

class BookRemoteImpl @Inject constructor(private val api: RemoteBookApi):
    BookDataStore {
    override suspend fun getBook(query: String, page: Int, size: Int): Response<BookData>
            = api.getBookSearch(query, page = page, size = size)
}