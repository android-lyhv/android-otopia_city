package vn.com.utopia.model.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import vn.com.utopia.model.entiies.City


/**
 * Created by Ly Ho V. on November 22, 2018
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
@Dao
interface DatabaseDao {
    @Query("SELECT * from cities LIMIT :offset, :limit")
    fun getCities(offset: Int, limit: Int): List<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<City>)

    @Query("DELETE FROM cities")
    fun deleteAllCities()
}
