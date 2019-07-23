package vn.com.utopia

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import vn.com.utopia.model.database.AppDatabase
import vn.com.utopia.model.database.DatabaseDao
import vn.com.utopia.model.entry.City
import java.util.*
import kotlin.collections.ArrayList

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private var databaseDate: DatabaseDao? = null


    @Before
    fun setup() {
        databaseDate = AppDatabase.getDatabase(InstrumentationRegistry.getTargetContext())?.dataDao()
    }

    @Test
    fun createRecordCity() {
        val dataDao = AppDatabase.getDatabase(InstrumentationRegistry.getContext())?.dataDao()
        val cities = ArrayList<City>()
        for (i in 0 until 1) {
            cities.add(City().apply {
                cityId = UUID.randomUUID().toString()
                country = "country $i"
                city = "city $i"
            })
        }
        dataDao?.deleteAllCities()
        dataDao?.insertCities(cities)
        val cities1 = dataDao?.getCities(0, 1)
        Log.d("aaaa", cities1?.size.toString())
        assertEquals(10, cities1?.size)
    }
}
