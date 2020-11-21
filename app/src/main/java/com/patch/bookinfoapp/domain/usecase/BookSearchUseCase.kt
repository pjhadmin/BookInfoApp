package com.patch.bookinfoapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.patch.bookinfoapp.domain.datadource.BookPageKeyDataSource
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.domain.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class BookSearchUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    var dataSource: BookPageKeyDataSource? = null
    private var queryString: String = ""

    fun initSearchBookListLiveData(viewModelScope: CoroutineScope): LiveData<PagedList<BookEntity.Book>> {
        val config: PagedList.Config = PagedList.Config.Builder()
            .setPageSize(BookRepository.DEFAULT_PAGE_SIZE)
            .setPrefetchDistance(20)
            .setEnablePlaceholders(false)
            .build()

        val dataSource = object : DataSource.Factory<Int, BookEntity.Book>() {
            override fun create(): DataSource<Int, BookEntity.Book> {
                return BookPageKeyDataSource(viewModelScope, bookRepository).apply {
                    query = queryString
                    dataSource = this
                }
            }
        }

        return LivePagedListBuilder(dataSource, config).build()
    }

    fun sendQueryString(query: String) {
        if (query.isNotEmpty()) {
            queryString = query
            dataSource?.invalidate()
        }
    }
}