package vn.com.utopia.model.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

/**
 * Created by Ly Ho V. on November 22, 2018
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
object MigrationUtils {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `cities` (`id` TEXT NOT NULL, " +
                        "`city` TEXT, " +
                        "`country` TEXT, " +
                        "`population` TEXT, " +
                        "PRIMARY KEY(`id`))"
            )
        }
    }
}
