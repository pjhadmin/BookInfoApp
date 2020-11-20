package com.patch.bookinfoapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.patch.bookinfoapp.domain.datadource.BookPageKeyDataSource
import com.patch.bookinfoapp.domain.entity.BookEntity
import com.patch.bookinfoapp.domain.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class BookSearchUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    companion object {
        const val DEBOUNCE_INTERVAL = 300L
    }

    var dataSource: BookPageKeyDataSource? = null
    private val queryString = ConflatedBroadcastChannel<String>()

    fun initSearchBookListLiveData(viewModelScope: CoroutineScope): LiveData<PagedList<BookEntity.Book>> {

        val config: PagedList.Config = PagedList.Config.Builder()
            .setPageSize(BookRepository.DEFAULT_PAGE_SIZE)
            .setPrefetchDistance(20)
            .setEnablePlaceholders(false)
            .build()

        val dataSource = object : DataSource.Factory<Int, BookEntity.Book>() {
            override fun create(): DataSource<Int, BookEntity.Book> {
                return BookPageKeyDataSource(viewModelScope, bookRepository).apply {
                    query = queryString.valueOrNull.orEmpty()
                    dataSource = this
                }
            }
        }

        return LivePagedListBuilder(dataSource, config).build()
    }


    fun initQueryChannel() =
        queryString.asFlow()
            .debounce(DEBOUNCE_INTERVAL)
            .onEach {
                dataSource?.invalidate()
            }


    fun sendQueryString(query: String) {
        if (query.isNotEmpty()) {
            queryString.offer(query)
        }
    }
}