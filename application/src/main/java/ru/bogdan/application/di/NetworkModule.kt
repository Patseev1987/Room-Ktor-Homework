package ru.bogdan.application.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bogdan.application.data.web.HttpClientApp
import ru.bogdan.application.data.web.RemoteRepository
import ru.bogdan.application.data.web.RemoteRepositoryImpl


@Module
interface NetworkModule {
    @AppScope
    @Binds
    fun bindRemoteRepository(impl: RemoteRepositoryImpl): RemoteRepository

    companion object {
        @AppScope
        @Provides
        fun provideHttpClient(): HttpClientApp {
            return HttpClientApp
        }
    }
}
