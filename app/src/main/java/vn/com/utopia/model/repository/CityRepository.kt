package vn.com.utopia.model.repository

import android.app.Application
import vn.com.utopia.model.entiies.City
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Ly Ho V. on July 23, 2019
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
class CityRepository(application: Application) : BaseRepository(application), ICityRepository {
    override suspend fun getCities(offset: Int, limit: Int): List<City> {
        return suspendCoroutine { continuation ->
            val cities = dataDao?.getCities(offset, limit)
            continuation.resume(cities ?: ArrayList())
        }
    }
}
