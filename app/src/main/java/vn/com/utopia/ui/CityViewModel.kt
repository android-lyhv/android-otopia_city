package vn.com.utopia.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import vn.com.utopia.model.entry.ICity
import vn.com.utopia.model.repository.ICityRepository

/**
 * Created by Ly Ho V. on July 23, 2019
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
class CityViewModel(application: Application, private val iCityRepository: ICityRepository) : AndroidViewModel(application) {
    val mCitiesLiveData = MutableLiveData<List<ICity>>()
    fun getCitiesAsync() {
        // TODO
    }
}
