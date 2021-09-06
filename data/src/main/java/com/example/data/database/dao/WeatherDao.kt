package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.entity.WeatherRoom

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    fun findAll(): List<WeatherRoom>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weatherList: List<WeatherRoom>)


}