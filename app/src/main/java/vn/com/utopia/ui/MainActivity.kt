package vn.com.utopia.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import vn.com.utopia.R
import vn.com.utopia.model.entry.ICity
import vn.com.utopia.model.repository.CityRepository

class MainActivity : AppCompatActivity() {
    private lateinit var mCityViewModel: CityViewModel
    private lateinit var mCityAdapter: CityRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Adapter
        mCityAdapter = CityRecyclerAdapter(this)
        recyclerCity.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = mCityAdapter
        }
        // Init ViewModel
        mCityViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CityViewModel(this@MainActivity.application, CityRepository()) as T
            }
        }).get(CityViewModel::class.java)
        // Load Cities
        mCityViewModel.getCitiesAsync()
        // Observer
        mCityViewModel.mCitiesLiveData.observe(this, Observer {
            showCities(it)
        })
    }

    private fun showCities(cities: List<ICity>?) {
        mCityAdapter.setElements(cities)
    }
}
