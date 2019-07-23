package vn.com.utopia.common

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by Ly Ho V. on 08 December 2017
 */
class RecyclerScrollMoreListener(private val mLayoutManager: RecyclerView.LayoutManager, currentPage: Int, visibleThreshold: Int, private val mLoadMoreListener: OnLoadMoreListener?) : RecyclerView.OnScrollListener() {
    private var mCurrentPage = 0
    private var mPreviousTotalItemCount = 0
    private var mIsLoading = true
    private var mVisibleThreshold = 5

    init {
        mVisibleThreshold = visibleThreshold
        mCurrentPage = currentPage
    }

    /**
     * handler for gitLayout
     *
     * @param lastVisibleItemPositions
     * @return
     */
    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (mLoadMoreListener != null) {
            var lastVisibleItemPosition = 0
            val totalItemCount = mLayoutManager.itemCount
            if (mLayoutManager is StaggeredGridLayoutManager) {
                val lastVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            } else if (mLayoutManager is LinearLayoutManager) {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
            } else if (mLayoutManager is GridLayoutManager) {
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
            }
            if (totalItemCount < mPreviousTotalItemCount) {
                mCurrentPage = 0
                mPreviousTotalItemCount = totalItemCount
                mIsLoading = totalItemCount == 0
            }

            if (mIsLoading && totalItemCount > mPreviousTotalItemCount) {
                mIsLoading = false
                mPreviousTotalItemCount = totalItemCount
            }

            if (!mIsLoading && lastVisibleItemPosition + mVisibleThreshold > totalItemCount) {
                mCurrentPage++
                mLoadMoreListener.onLoadMore(mVisibleThreshold, totalItemCount)
                mIsLoading = true
            }
        }
    }

    interface OnLoadMoreListener {
        fun onLoadMore(limit: Int, skip: Int)
    }
}
