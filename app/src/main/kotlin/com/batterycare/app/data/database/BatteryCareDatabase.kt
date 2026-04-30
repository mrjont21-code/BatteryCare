package com.batterycare.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.batterycare.app.data.database.dao.BatteryDao
import com.batterycare.app.data.model.BatteryRecord

@Database(entities = [BatteryRecord::class], version = 1, exportSchema = false)
abstract class BatteryCareDatabase : RoomDatabase() {

    abstract fun batteryDao(): BatteryDao

    companion object {
        @Volatile
        private var INSTANCE: BatteryCareDatabase? = null

        fun getDatabase(context: Context): BatteryCareDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BatteryCareDatabase::class.java,
                    "battery_care_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
