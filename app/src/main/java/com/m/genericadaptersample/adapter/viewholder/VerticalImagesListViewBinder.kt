package com.m.genericadaptersample.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m.genericadaptersample.*
import com.m.genericadaptersample.adapter.models.VeriticalImageInnerModel
import com.m.genericadaptersample.adapter.models.VerticalImageListModel
import com.m.genericadaptersample.adapter.models.VeriticalImageModel
import kotlinx.android.synthetic.main.adapter_recyclerview_vertical.view.*

class VerticalImagesListViewBinder(val block : (data: VeriticalImageInnerModel) -> Unit)
    : FeedItemViewBinder<VerticalImageListModel, VerticalImagesListViewHolder>(
    VerticalImageListModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): VerticalImagesListViewHolder {
        return VerticalImagesListViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: VerticalImageListModel, viewHolder: VerticalImagesListViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.adapter_recyclerview_vertical

    override fun areContentsTheSame(oldItem: VerticalImageListModel, newItem: VerticalImageListModel) = oldItem.Images == newItem.Images

    override fun areItemsTheSame(oldItem: VerticalImageListModel, newItem: VerticalImageListModel) : Boolean {
        return oldItem.id == newItem.id
    }
}


class VerticalImagesListViewHolder(val view : View, val block : (data: VeriticalImageInnerModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: VerticalImageListModel) {

        var adapter : FeedAdapter? = null

        itemView.setOnClickListener {

        }

        itemView.apply {
            val verticalImagesViewBinder = VerticalImagesInnerViewBinder { horizontalImageModel : VeriticalImageInnerModel ->
                block(horizontalImageModel)}
            val viewBinders = mutableMapOf<FeedItemClass, FeedItemBinder>()
            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                verticalImagesViewBinder.modelClass,
                verticalImagesViewBinder as FeedItemBinder)
            adapter = FeedAdapter(viewBinders)
            tv_horizontal_header_v?.text = data.title
            adapter_recycllerview_v?.apply {

                layoutManager = LinearLayoutManager(adapter_recycllerview_v?.context,
                    LinearLayoutManager.HORIZONTAL,false)
                if (adapter_recycllerview_v?.adapter == null) {
                    adapter_recycllerview_v?.adapter = adapter
                }
                (adapter_recycllerview_v?.adapter as FeedAdapter).submitList(
                    data.Images as List<Any>? ?: emptyList())
            }
        }
    }
}