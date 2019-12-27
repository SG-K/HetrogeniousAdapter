package com.m.genericadaptersample.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m.genericadaptersample.*
import com.m.genericadaptersample.adapter.models.HorizontalImageListModel
import com.m.genericadaptersample.adapter.models.HorizontalImageModel
import kotlinx.android.synthetic.main.adapter_recycleriew.view.*

class HorizontalImagesListViewBinder(val block : (data: HorizontalImageModel) -> Unit)
    : FeedItemViewBinder<HorizontalImageListModel, HorizontalImagesListViewHolder>(
    HorizontalImageListModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): HorizontalImagesListViewHolder {
        return HorizontalImagesListViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: HorizontalImageListModel, viewHolder: HorizontalImagesListViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.adapter_recycleriew

    override fun areContentsTheSame(oldItem: HorizontalImageListModel, newItem: HorizontalImageListModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: HorizontalImageListModel, newItem: HorizontalImageListModel) : Boolean {
        return oldItem.id == newItem.id
    }
}


class HorizontalImagesListViewHolder(val view : View, val block : (data: HorizontalImageModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: HorizontalImageListModel) {

        var adapter : FeedAdapter? = null

        itemView.setOnClickListener {

        }

        itemView.apply {
            val horizontalImagesViewBinder = HorizontalImagesViewBinder { horizontalImageModel : HorizontalImageModel ->
                block(horizontalImageModel)}
            val viewBinders = mutableMapOf<FeedItemClass, FeedItemBinder>()
            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                horizontalImagesViewBinder.modelClass,
                horizontalImagesViewBinder as FeedItemBinder)
            adapter = FeedAdapter(viewBinders)
            tv_horizontal_header?.text = data.title
            adapter_recycllerview?.apply {

                layoutManager = LinearLayoutManager(adapter_recycllerview?.context,
                    LinearLayoutManager.HORIZONTAL,false)
                if (adapter_recycllerview?.adapter == null) {
                    adapter_recycllerview?.adapter = adapter
                }
                (adapter_recycllerview?.adapter as FeedAdapter).submitList(
                    data.Images as List<Any>? ?: emptyList())
            }
        }
    }
}