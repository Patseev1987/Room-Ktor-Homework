package ru.bogdan.application.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.bogdan.application.ViewModelFactory
import javax.inject.Named

@Subcomponent(
    modules = [DetailsViewModelModule::class]
)
interface EmployeeDetailsViewModelComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance @Named("employeeId") employeeId: String,
        ): EmployeeDetailsViewModelComponent
    }
}