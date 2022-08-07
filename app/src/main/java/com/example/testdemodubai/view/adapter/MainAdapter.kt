package com.example.testdemodubai.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testdemodubai.R
import com.example.testdemodubai.model.data.Result

class MainAdapter(var context: Context, var list:List<Result>,var onClickListener: AdapterItemClick) : RecyclerView.Adapter<MainAdapter.viewHolder>() {

    inner class viewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        var thumbnailHolder=itemview.findViewById<ImageView>(R.id.thumbnail)
        var headline=itemview.findViewById<TextView>(R.id.headline)
        var authors=itemview.findViewById<TextView>(R.id.authors)
        var date=itemview.findViewById<TextView>(R.id.date)
        init {

            itemview.setOnClickListener{
                onClickListener.onClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LayoutInflater.from(context).inflate(R.layout.article_item,parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

            if (list[position].media.isNotEmpty())
            Glide.with(context)
                .load(list[position].media[0].mediaMetadata[0].url)
                .into(holder.thumbnailHolder)
        holder.headline.text=list[position].title
        holder.authors.text=list[position].byline
        holder.date.text=list[position].publishedDate

    }

    override fun getItemCount(): Int {
        return list.size
    }
}