package com.android.carousell.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.carousell.R
import com.android.carousell.models.NewsDo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_cell.view.*
import java.util.*
import com.android.carousell.utils.*

class NewsAdapter(var newsList: MutableList<NewsDo>, var onClickListener: (NewsDo) ->Unit) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun refreshList(list: MutableList<NewsDo>) {
        newsList.clear()
        newsList = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_cell, parent, false),onClickListener)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class NewsViewHolder(private val view: View, private val onClickListener: (NewsDo) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(newsDo: NewsDo) {
            val createdDate = Calendar.getInstance()
            createdDate.timeInMillis = newsDo.time_created?:0*1000
            view.title.text = newsDo.title
            view.description.text = newsDo.description
            view.lastUpdatedAt.text = createdDate.getTimeInText()
            Glide.with(view.context).load(newsDo.banner_url).into(view.bannerImage)
            view.newsCard.setOnClickListener { onClickListener.invoke(newsDo) }
        }
    }
}