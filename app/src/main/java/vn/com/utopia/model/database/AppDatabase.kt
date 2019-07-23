package vn.com.utopia.model.database

import DatabaseConfig
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import vn.com.utopia.model.database.helper.AssetSQLiteOpenHelperFactory
import vn.com.utopia.model.entiies.City


@Database(
    entities = [City::class],
    version = DatabaseConfig.DB_SCHEMA_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao(): DatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        internal fun getDatabase(context: Context, isTestMode: Boolean = false): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        if (isTestMode) {
                            return Room.databaseBuilder(
                                context, AppDatabase::class.java,
                                DatabaseConfig.DB_NAME_TEST
                            ).allowMainThreadQueries()
                                .addMigrations(MigrationUtils.MIGRATION_1_2)
                                .build()

                        } else {
                            return Room.databaseBuilder(
                                context, AppDatabase::class.java,
                                DatabaseConfig.DB_NAME
                            ).openHelperFactory(AssetSQLiteOpenHelperFactory())
                                .addMigrations(MigrationUtils.MIGRATION_1_2)
                                .build()
                        }

                    }
                }
            }
            return INSTANCE
        }

    }
}