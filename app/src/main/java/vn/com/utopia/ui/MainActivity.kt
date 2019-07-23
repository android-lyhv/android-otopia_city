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
import vn.com.utopia.common.RecyclerScrollMoreListener
import vn.com.utopia.model.entry.ICity
import vn.com.utopia.model.repository.CityRepository

class MainActivity : AppCompatActivity(), RecyclerScrollMoreListener.OnLoadMoreListener {
    // Load database sync
    override fun onLoadMore(limit: Int, skip: Int) {
        mCityViewModel.getCitiesAsync()
    }

    private lateinit var mCityViewModel: CityViewModel
    private lateinit var mCityAdapter: CityRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Adapter
        mCityAdapter = CityRecyclerAdapter(this)
        val layoutManager = LinearLayoutManager(this)
        recyclerCity.apply {
            this.layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            addOnScrollListener(RecyclerScrollMoreListener(layoutManager, 0, 10, this@MainActivity))
            adapter = mCityAdapter
        }
        // Init ViewModel
        mCityViewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CityViewModel(this@MainActivity.application, CityRepository()) as T
            }
        }).get(CityViewModel::class.java)
        // Observer
        mCityViewModel.mCitiesLiveData.observe(this, Observer {
            showNewCities(it)
        })
        // Load Cities
        onLoadMore(10, 0)
    }

    private fun showNewCities(cities: List<ICity>?) {
        mCityAdapter.addElements(cities)
    }
}
