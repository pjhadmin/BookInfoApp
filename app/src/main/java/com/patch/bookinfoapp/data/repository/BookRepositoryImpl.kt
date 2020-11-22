package com.patch.bookinfoapp.data.repository

import com.patch.bookinfoapp.data.entity.BookData
import com.patch.bookinfoapp.domain.repository.BookRepository
import retrofit2.Response
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    // 현재 Remote만 있으나, Local Repository에 대한 명세를 추가할 수 있음
    private val remote: BookRemoteImpl
): BookRepository {

    override suspend fun getBook(query: String, page: Int, size: Int): BookData
            = remote.getBook(query, page = page, size = size)
}