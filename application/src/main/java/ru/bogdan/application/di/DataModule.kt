package ru.bogdan.application.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.bogdan.application.data.AppRepositoryImpl
import ru.bogdan.application.data.db.AppDatabase
import ru.bogdan.application.data.db.DBRepository
import ru.bogdan.application.data.db.DBRepositoryImpl
import ru.bogdan.application.domain.AppRepository


@Module
interface DataModule {
    @AppScope
    @Binds
    fun provideDBRepository(impl: DBRepositoryImpl): DBRepository

    @AppScope
    @Binds
    fun provideAppRepository(impl: AppRepositoryImpl): AppRepository

    companion object {
        @AppScope
        @Provides
        fun provideDB(
            context: Context
        ): AppDatabase {
            return AppDatabase.getInstance(context)
        }

        @AppScope
        @Provides
        fun provideCoroutineDispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}