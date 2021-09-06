package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.database.dao.WeatherDao
import com.example.data.database.entity.WeatherRoom

@Database(entities = [WeatherRoom::class], version = 1)
abstract class WeatherDataBase : RoomDatabase(){

    companion object {

        private const val DATABASE_NAME = "weather_database"
        private lateinit var instance: WeatherDataBase

        @Synchronized
        fun getInstance(context: Context): WeatherDataBase {
            if (!this::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDataBase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }

    }

    abstract fun weatherDao(): WeatherDao

}