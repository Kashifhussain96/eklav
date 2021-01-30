package com.mytvapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView


class FolderAdapter(private  val listData: MutableList<String?>?,private val listener: OnClickFolderLister?) : RecyclerView.Adapter<FolderAdapter.ViewHolder>() {




    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var textView: AppCompatTextView

        init {
            textView = itemView.findViewById(R.id.tvTitle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(com.mytvapp.R.layout.folder_layout, parent, false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return listData?.size!!
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = listData!![position]

        holder.textView.setOnClickListener { listener?.onClickItem(listData!![position]!!)}
    }
}