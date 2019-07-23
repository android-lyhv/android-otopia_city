package vn.com.utopia.model.entry

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by Ly Ho V. on April 17, 2018
 */
@Entity(tableName = "city_table")
class City : ICity {
    override fun getId(): Int {
        return cityId
    }

    override fun getCityName(): String {
        return name
    }

    override fun getCityAddress(): String {
        return address
    }

    override fun getCountTitle(): String {
        // TODO
        return ""
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var cityId: Int = 0
    @ColumnInfo(name = "name")
    var name: String = ""
    @ColumnInfo(name = "address")
    var address: String = ""
    @ColumnInfo(name = "count")
    var count: Int = 0
}