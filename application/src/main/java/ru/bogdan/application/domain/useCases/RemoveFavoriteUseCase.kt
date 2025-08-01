package ru.bogdan.application.domain.useCases

import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(employee: Employee): Boolean = appRepository.removeFavoriteEmployee(employee)
}