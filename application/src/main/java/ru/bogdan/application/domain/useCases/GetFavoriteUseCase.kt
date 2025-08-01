package ru.bogdan.application.domain.useCases

import kotlinx.coroutines.flow.Flow
import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class GetFavoriteUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(): Flow<List<Employee>> = appRepository.getFavoriteEmployees()
}