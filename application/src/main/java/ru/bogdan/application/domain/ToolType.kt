package ru.bogdan.application.domain

import kotlinx.serialization.Serializable

@Serializable
enum class ToolType {
    CUTTER, INNER, HOLDER, MILLING, DRILL
}