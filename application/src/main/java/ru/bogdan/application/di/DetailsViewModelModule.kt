package ru.bogdan.application.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.bogdan.application.presentor.ui.details.DetailsEmployeeViewModel


@Module
interface DetailsViewModelModule {

    @IntoMap
    @ViewModelKey(DetailsEmployeeViewModel::class)
    @Binds
    fun bindDetailsViewModelViewModel(impl: DetailsEmployeeViewModel): ViewModel
}