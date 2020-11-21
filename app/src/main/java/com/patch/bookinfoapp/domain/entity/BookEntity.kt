package com.patch.bookinfoapp.domain.entity

import android.os.Parcelable
import com.patch.bookinfoapp.common.util.percentageOnlyPositive
import com.patch.bookinfoapp.data.entity.BookData
import kotlinx.android.parcel.Parcelize


data class BookEntity(
    val bookList: List<Book> = listOf(),
    val isEndPage: Boolean = false,
    val totalCount: Int = 0
) {
    @Parcelize
    data class Book(
        var title: String,
        var contents: String,
        var detailUrl: String,
        var isbn: String,
        var datetime: String,
        var authors: List<String>,
        var publisher: String,
        var translators: List<String>,
        var price: Int,
        var salePrice: Int,
        var thumbnail: String,
        var sellingStatus: String,
        var isLike: Boolean = false
    ) : Parcelable {
        val salePercentage: Int
            get() = price.percentageOnlyPositive(salePrice)
    }
}

object BookDataMapper {
    fun mapToBookEntity(data: BookData): BookEntity  {
        return BookEntity(mapToBookDocuments(data.documents), data.metaData.isEnd, data.metaData.totalCount)
    }

    fun mapToBookDocuments(data: List<BookData.Documents>): List<BookEntity.Book>
            = data.map {documentsToBook(it)}

    fun documentsToBook(data: BookData.Documents): BookEntity.Book =
        BookEntity.Book(
            title = data.title,
            contents = data.contents,
            detailUrl = data.detailUrl,
            isbn = data.isbn,
            datetime = data.datetime,
            authors = data.authors,
            publisher = data.publisher,
            translators = data.translators,
            price = data.price,
            salePrice = data.salePrice,
            thumbnail = data.thumbnail,
            sellingStatus = data.sellingStatus
        )
}