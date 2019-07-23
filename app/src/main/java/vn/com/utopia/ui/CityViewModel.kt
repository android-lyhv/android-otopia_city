package vn.com.utopia.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.com.utopia.model.entry.ICity
import vn.com.utopia.model.repository.ICityRepository

/**
 * Created by Ly Ho V. on July 23, 2019
 * Copyright © 2017 Ly Ho V. All rights reserved.
 */
class CityViewModel(application: Application, private val iCityRepository: ICityRepository) :
    AndroidViewModel(application) {
    val mCitiesLiveData = MutableLiveData<List<ICity>>()

    fun getCitiesAsync(offset: Int, limit: Int) {
        MainScope().launch {
            val cities = withContext(Dispatchers.Default) { iCityRepository.getCities(offset, limit) }
            mCitiesLiveData.postValue(cities)
        }
    }
}
