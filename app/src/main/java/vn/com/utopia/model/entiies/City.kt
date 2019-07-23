package vn.com.utopia.model.entiies

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.text.DecimalFormat

/**
 * Created by Ly Ho V. on April 17, 2018
 */
@Entity(tableName = "cities")
class City : ICity {
    override fun getId(): String {
        return cityId
    }

    override fun getCityName(): String {
        return city
    }

    override fun getCountryName(): String {
        return country
    }

    override fun getPopulationTitle(): String {
        return DecimalFormat("#,###.##").format(population)
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var cityId: String = ""
    @ColumnInfo(name = "city")
    var city: String = ""
    @ColumnInfo(name = "country")
    var country: String = ""
    @ColumnInfo(name = "population")
    var population: Int = 0
}