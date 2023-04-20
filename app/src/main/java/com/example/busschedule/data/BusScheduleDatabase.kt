package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusScheduleDatabase : RoomDatabase() {

    abstract fun busScheduleDao(): BusScheduleDao

    companion object {

        private const val DATABASE_NAME = "bus_schedule.db"

        private var instance: BusScheduleDatabase? = null

        fun getDatabase(context: Context): BusScheduleDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context = context,
                    klass = BusScheduleDatabase::class.java,
                    name = DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }

    }

}