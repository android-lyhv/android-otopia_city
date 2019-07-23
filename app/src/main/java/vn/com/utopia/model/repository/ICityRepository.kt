package vn.com.utopia.model.repository

import vn.com.utopia.model.entiies.City

/**
 * Created by Ly Ho V. on July 23, 2019
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
interface ICityRepository {
    suspend fun getCities(offset: Int, limit: Int): List<City>
}
