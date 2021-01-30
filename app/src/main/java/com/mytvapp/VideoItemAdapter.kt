package com.mytvapp

import android.R.attr.path
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView


class VideoItemAdapter(private  val listData: MutableList<String?>?,private val listener: OnClickVideoListener?) : RecyclerView.Adapter<VideoItemAdapter.ViewHolder>() {




    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var iv: AppCompatImageView = itemView.findViewById(R.id.ivThumbnail)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.video_item, parent, false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return listData?.size!!
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val thumb = ThumbnailUtils.createVideoThumbnail(listData?.get(position)!!, MediaStore.Images.Thumbnails.MINI_KIND)
//
//        holder.iv.setImageBitmap(thumb)
        holder.iv.setOnClickListener { listener?.onClickItem(listData!![position]!!)}
    }
}