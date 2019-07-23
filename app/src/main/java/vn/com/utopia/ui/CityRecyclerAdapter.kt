package vn.com.utopia.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import vn.com.utopia.R
import vn.com.utopia.common.BaseRecyclerAdapter
import vn.com.utopia.common.BaseViewHolder
import vn.com.utopia.model.entry.ICity


/**
 * Created by Ly Ho V. on April 08, 2018
 */
class CityRecyclerAdapter(context: Context) : BaseRecyclerAdapter<ICity, CityRecyclerAdapter.CityViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_citiy, parent, false)
        return CityViewHolder(view)
    }

    inner class CityViewHolder(itemView: View) : BaseViewHolder(itemView) {
        private val mTvCityName: TextView = itemView.findViewById(R.id.tvCityName)
        private val mTvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        private val mTvCount: TextView = itemView.findViewById(R.id.tvCount)
        override fun onBind() {
            getElement(adapterPosition)?.let {
                mTvCityName.text = it.getCityName()
                mTvAddress.text = it.getCityAddress()
                mTvCount.text = it.getCountTitle()
            }
        }
    }
}
