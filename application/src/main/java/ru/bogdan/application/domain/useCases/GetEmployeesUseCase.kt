package ru.bogdan.application.domain.useCases

import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(): Result<List<Employee>> = appRepository.getEmployees()
}