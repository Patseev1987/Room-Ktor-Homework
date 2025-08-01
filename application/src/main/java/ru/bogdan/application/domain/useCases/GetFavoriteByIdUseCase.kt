package ru.bogdan.application.domain.useCases

import ru.bogdan.application.domain.AppRepository
import ru.bogdan.application.domain.Employee
import javax.inject.Inject

class GetFavoriteByIdUseCase @Inject constructor(
    private val appRepository: AppRepository
) {
    suspend operator fun invoke(id: String): Employee? = appRepository.getFavoriteById(id)
}