package ru.bogdan.application.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.bogdan.application.presentor.ui.employee.EmployeesViewModel
import ru.bogdan.application.presentor.ui.favorite.FavoritesViewModel


@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(EmployeesViewModel::class)
    @Binds
    fun bindProductsListViewModel(impl: EmployeesViewModel): ViewModel

    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    @Binds
    fun bindFavoritesViewModel(impl: FavoritesViewModel): ViewModel
}