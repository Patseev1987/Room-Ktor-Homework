package ru.bogdan.application.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.bogdan.application.data.db.dao.DaoEmployeeDB
import ru.bogdan.application.data.db.dao.DaoEmployeeDBToolDBCross
import ru.bogdan.application.data.db.dao.DaoEmployeeDBWithToolDB
import ru.bogdan.application.data.db.dao.DaoToolDB
import ru.bogdan.application.data.db.model.EmployeeDB
import ru.bogdan.application.data.db.model.EmployeeDBToolDBCross
import ru.bogdan.application.data.db.model.ToolDB

@Database(
    entities = [ToolDB::class, EmployeeDB::class, EmployeeDBToolDBCross::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val daoEmployeeDBWithToolDB: DaoEmployeeDBWithToolDB
    abstract val daoEmployeeDB: DaoEmployeeDB
    abstract val daoToolDB: DaoToolDB
    abstract val daoEmployeesDBToolDBCross: DaoEmployeeDBToolDBCross

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_db"
                    )
                        .fallbackToDestructiveMigration(false)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
