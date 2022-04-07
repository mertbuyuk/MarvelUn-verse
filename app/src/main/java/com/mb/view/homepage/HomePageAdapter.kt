package com.mb.view.homepage

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mb.marveluniverse.databinding.CharacterlistItemBinding
import com.mb.model.Results

class HomePageAdapter : ListAdapter<Results,HomePageAdapter.HomePageViewHolder>(DIFF_CALLBACK) {

    class HomePageViewHolder(private val binding: CharacterlistItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(hero : Results){

            binding.heroNameTxt.text = hero.name

            Glide.with(binding.root)
                .load(hero.thumbnail.path+"."+hero.thumbnail.extension)
                .circleCrop()
                .into(binding.heroImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomePageAdapter.HomePageViewHolder {
        val binding = CharacterlistItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomePageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomePageAdapter.HomePageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Results, newItem: Results) =
                oldItem == newItem
        }
    }
}