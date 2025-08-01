package ru.bogdan.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.bogdan.application.MainActivity

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun getViewModelComponentFactory(): EmployeeDetailsViewModelComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}