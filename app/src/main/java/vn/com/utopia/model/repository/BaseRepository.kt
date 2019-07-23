package vn.com.utopia.model.repository

import android.app.Application
import vn.com.utopia.model.database.AppDatabase
import vn.com.utopia.model.database.DatabaseDao


/**
 * Created by Ly Ho V. on November 22, 2018
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
abstract class BaseRepository constructor(application: Application) {
    protected var dataDao: DatabaseDao? = AppDatabase.getDatabase(application)?.dataDao()
}
