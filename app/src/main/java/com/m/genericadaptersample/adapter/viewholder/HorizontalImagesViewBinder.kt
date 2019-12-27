package com.m.genericadaptersample.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.m.genericadaptersample.FeedItemViewBinder
import com.m.genericadaptersample.R
import com.m.genericadaptersample.adapter.models.HorizontalImageModel
import kotlinx.android.synthetic.main.adapter_horizontal_image.view.*

class HorizontalImagesViewBinder(val block : (data: HorizontalImageModel) -> Unit) : FeedItemViewBinder<HorizontalImageModel, HorizontalImagesViewHolder>(
    HorizontalImageModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): HorizontalImagesViewHolder {
        return HorizontalImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: HorizontalImageModel, viewHolder: HorizontalImagesViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.adapter_horizontal_image

    override fun areContentsTheSame(oldItem: HorizontalImageModel, newItem: HorizontalImageModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: HorizontalImageModel, newItem: HorizontalImageModel) : Boolean {
        return oldItem.Image == newItem.Image
    }
}


class HorizontalImagesViewHolder(val view : View, val block : (data: HorizontalImageModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: HorizontalImageModel) {

        itemView.setOnClickListener {
            block(data)
        }

        itemView.apply {
            Glide
                .with(itemView.context)
                .load(data.Image)
                .centerCrop()
                .into(im_horizontal)
        }
    }
}