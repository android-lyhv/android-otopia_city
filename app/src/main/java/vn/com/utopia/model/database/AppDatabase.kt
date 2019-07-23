package vn.com.utopia.model.database

import DatabaseConfig
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import vn.com.utopia.model.database.helper.AssetSQLiteOpenHelperFactory
import vn.com.utopia.model.entry.City


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

        internal fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        val builder = Room.databaseBuilder(
                            context, AppDatabase::class.java,
                            DatabaseConfig.DB_NAME
                        )
                        return builder.openHelperFactory(AssetSQLiteOpenHelperFactory())
                            .addMigrations(MigrationUtils.MIGRATION_1_2)
                            .build()
                    }
                }
            }
            return INSTANCE
        }

    }
}