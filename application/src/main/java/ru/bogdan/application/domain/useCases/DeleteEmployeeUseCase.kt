package ru.bogdan.application.domain.useCases

import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class DeleteEmployeeUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(employee: Employee): Result<Boolean> = appRepository.deleteEmployee(employee)
}