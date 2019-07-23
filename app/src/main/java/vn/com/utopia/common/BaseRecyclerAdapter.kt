package vn.com.utopia.common

import android.content.Context
import android.support.v7.widget.RecyclerView

/**
 * Base Adapter.
 */
abstract class BaseRecyclerAdapter<T, VH : BaseViewHolder?>(protected val mContext: Context) : RecyclerView.Adapter<VH>() {
    private var mElements: MutableList<T>? = null
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.onBind()
    }

    fun addElements(elements: List<T>?) {
        if (mElements == null) {
            mElements = ArrayList()
        }
        if (elements?.isNotEmpty() == true) {
            mElements?.addAll(elements)
            notifyItemRangeInserted(itemCount, elements.size)
        }
    }

    fun setElements(elements: List<T>?) {
        if (mElements == null) {
            mElements = ArrayList()
        }
        elements?.let {
            mElements?.clear()
            mElements?.addAll(it)
            notifyDataSetChanged()
        }
    }

    fun getElement(position: Int): T? {
        return try {
            mElements!![position]
        } catch (e: Exception) {
            null
        }
    }

    fun removeItem(position: Int) {
        try {
            mElements?.removeAt(position)
            notifyItemRemoved(position)
        } catch (ex: Exception) {
            // No-op
        }
    }

    fun updateItem(position: Int, item: T) {
        try {
            mElements?.set(position, item)
            notifyItemChanged(position)
        } catch (ex: Exception) {
            // No-op
        }
    }

    fun getElements(): MutableList<T> {
        return mElements ?: ArrayList()
    }

    override fun getItemCount(): Int {
        return mElements?.size ?: 0
    }
}
