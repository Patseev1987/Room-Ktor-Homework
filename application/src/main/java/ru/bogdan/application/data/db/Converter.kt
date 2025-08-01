package ru.bogdan.application.data.db

import androidx.room.TypeConverter
import ru.bogdan.application.domain.ToolType

class Converter {
    @TypeConverter
    fun stringFromStage(value: ToolType?): String? = value?.name

    @TypeConverter
    fun stringToStage(str: String?): ToolType? = if (str != null) ToolType.valueOf(str) else null
}