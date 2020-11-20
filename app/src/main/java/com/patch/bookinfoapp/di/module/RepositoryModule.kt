package com.patch.bookinfoapp.di.module

import com.patch.bookinfoapp.data.repository.BookDataStore
import com.patch.bookinfoapp.data.repository.BookRemoteImpl
import com.patch.bookinfoapp.data.repository.BookRepositoryImpl
import com.patch.bookinfoapp.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBookRemoteImpl(bookRemoteImpl: BookRemoteImpl): BookDataStore

    @Binds
    abstract fun bindBookRepositoryImpl(bookRepository: BookRepositoryImpl): BookRepository
}