package ru.bogdan.application.domain.useCases

import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class GetEmployeeByIdUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(id: String): Result<Employee> = appRepository.getEmployeeById(id)
}