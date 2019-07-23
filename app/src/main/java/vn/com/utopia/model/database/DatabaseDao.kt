import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import vn.com.utopia.model.entry.City

/**
 * Created by Ly Ho V. on November 22, 2018
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
@Dao
interface DatabaseDao {
    @Query("SELECT * from city_table")
    fun getCities(userId: Int?, videoTagId: Int?): List<City>
}
