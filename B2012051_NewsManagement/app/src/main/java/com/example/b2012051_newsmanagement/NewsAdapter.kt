package com.example.b2012051_newsmanagement

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.b2012051_newsmanagement.constant.Constants
import com.example.b2012051_newsmanagement.databinding.ItemNewsBinding
import com.example.b2012051_newsmanagement.data.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private lateinit var binding : ItemNewsBinding
    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
       var inflater = LayoutInflater.from(parent.context)
        binding = ItemNewsBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item : News) {
            binding.apply {
                tvTitle.text = item.newsTitle;
                tvDesc.text = item.newsDesc;

                root.setOnClickListener {
                    val intent = Intent(context, UpdateNewsActivity::class.java)
                    intent.putExtra(Constants.BUNDLE_NEWS_ID, item.newsId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.newsId == newItem.newsId
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}