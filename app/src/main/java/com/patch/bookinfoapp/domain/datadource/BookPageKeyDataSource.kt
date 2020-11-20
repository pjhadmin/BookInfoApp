package com.patch.bookinfoapp.domain.datadource

import androidx.paging.PageKeyedDataSource
import com.patch.bookinfoapp.common.manager.OnResponseListener
import com.patch.bookinfoapp.common.manager.request
import com.patch.bookinfoapp.data.entity.BookData
import com.patch.bookinfoapp.domain.entity.BookDataMapper.mapToBookEntity
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.domain.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class BookPageKeyDataSource constructor(
    private val coroutineScope: CoroutineScope,
    private val bookRepository: BookRepository): PageKeyedDataSource<Int, BookEntity.Book>() {

    var query: String = ""

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BookEntity.Book>
    ) {
        if(query.isNotEmpty()) {
            runBlocking(coroutineScope.coroutineContext) {
                launch {
                    bookRepository.getBook(query, 1).request(object :
                        OnResponseListener<BookData> {
                        override fun onSuccess(response: BookData?) {
                            response?.let {
                                callback.onResult(mapToBookEntity(it).bookList, null, 2)
                            }
                        }
                    })
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BookEntity.Book>) {
        if(query.isNotEmpty()) {
            runBlocking(coroutineScope.coroutineContext) {
                launch {
                    bookRepository.getBook(query, params.key).request(object : OnResponseListener<BookData>{
                        override fun onSuccess(response: BookData?) {
                            response?.let {
                                if (!it.metaData.isEnd)
                                    callback.onResult(mapToBookEntity(it).bookList, params.key + 1)
                            }
                        }
                    })
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BookEntity.Book>) {}
}