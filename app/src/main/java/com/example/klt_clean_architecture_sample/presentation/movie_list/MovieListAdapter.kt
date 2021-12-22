package com.example.klt_clean_architecture_sample.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.klt_clean_architecture_sample.databinding.ItemMovieBinding
import com.example.klt_clean_architecture_sample.domain.model.ListMovie

class MovieListAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<ListMovie, RecyclerView.ViewHolder>(newsDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MovieListViewHolder).bind(getItem(position))
    }

    companion object {
        val newsDiffUtil = object : DiffUtil.ItemCallback<ListMovie>() {
            override fun areItemsTheSame(oldItem: ListMovie, newItem: ListMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListMovie, newItem: ListMovie): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getClickItem(position: Int): ListMovie = getItem(position)

    inner class MovieListViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListMovie?) {

            item?.let {
                with(it) {
                    Glide.with(itemView.context).load(image).into(binding.imageView)
                    binding.rate.text = rate
                    binding.title.text = title
                }
            }
            itemView.setOnClickListener { onClick(adapterPosition) }
        }
    }
}