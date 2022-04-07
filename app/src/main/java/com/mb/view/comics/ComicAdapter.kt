package com.mb.view.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mb.marveluniverse.R
import com.mb.marveluniverse.databinding.ComicslistItemBinding

import com.mb.model.Item


class ComicAdapter : ListAdapter<Item,ComicAdapter.ComicViewHolder>(DIFF_CALLBACK) {
    class ComicViewHolder(private val binding : ComicslistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic : Item){

            binding.comicName.text = comic.name

            Glide.with(binding.root)
                .load(R.mipmap.superh)
                .circleCrop()
                .into(binding.comicImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicAdapter.ComicViewHolder {
        val binding = ComicslistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicAdapter.ComicViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem:  Item) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem:  Item, newItem:  Item) =
                oldItem == newItem
        }
    }
}