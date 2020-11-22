package com.patch.bookinfoapp.domain.datadource

import androidx.paging.PageKeyedDataSource
import com.patch.bookinfoapp.common.corourine.StateCoroutine
import com.patch.bookinfoapp.common.corourine.StateCoroutineImpl
import com.patch.bookinfoapp.domain.entity.BookDataMapper.mapToBookEntity
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.domain.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking


class BookPageKeyDataSource constructor(
    private val coroutineScope: CoroutineScope,
    private val bookRepository: BookRepository): PageKeyedDataSource<Int, BookEntity.Book>(), StateCoroutine by StateCoroutineImpl() {

    var query: String = ""

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BookEntity.Book>
    ) {
        if(query.isNotEmpty()) {
            runBlocking(coroutineScope.coroutineContext) {
                stateLaunch {
                    bookRepository.getBook(query, 1).apply {
                        callback.onResult(mapToBookEntity(this).bookList, null, 2)
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BookEntity.Book>) {
        if(query.isNotEmpty()) {
            runBlocking(coroutineScope.coroutineContext) {
                stateLaunch {
                    bookRepository.getBook(query, params.key).apply {
                        if (!metaData.isEnd)
                            callback.onResult(mapToBookEntity(this).bookList, params.key + 1)
                    }
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BookEntity.Book>) {}
}