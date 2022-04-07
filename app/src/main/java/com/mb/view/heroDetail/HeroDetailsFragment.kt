package com.mb.view.heroDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mb.marveluniverse.databinding.FragmentHeroDetailsBinding
import com.mb.model.Item
import com.mb.model.Results
import com.mb.view.comics.ComicAdapter
import com.mb.viewmodel.heroDetailViewModel.HeroDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Matcher
import java.util.regex.Pattern
import androidx.appcompat.app.AppCompatActivity
import java.lang.NumberFormatException
import androidx.appcompat.app.ActionBar
import com.mb.marveluniverse.R


@AndroidEntryPoint
class HeroDetailsFragment : Fragment() {
    private val args : HeroDetailsFragmentArgs by navArgs()
    private val viewModel : HeroDetailsViewModel by viewModels()

    private val adapter = ComicAdapter()

    private lateinit var binding : FragmentHeroDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHeroDetailsBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.comicRec.adapter = adapter

        viewModel.getCharacterDetails(args.id).observe(viewLifecycleOwner,{
            when(it.status){
                com.mb.utils.Resource.Status.SUCCESS -> onSucces(it.data?.data?.results)
            }
        })
    }

    private fun onSucces(results: List<Results>?) {

        if (!results?.get(0)!!.description.isNullOrEmpty()){
            binding.detailHeroDesc.text = results?.get(0)!!.description
        }
        binding.detailHeroName.text = results?.get(0)!!.name
        Glide.with(this)
            .load("${results?.get(0)!!.thumbnail.path}.${results?.get(0)!!.thumbnail.extension}")
            .into(binding.detailHeroImage)

        val list = orderList(results[0].comics.items)
        var len = 9
        if (list.size<10) len = list.size-1
        adapter.submitList(list.slice(0..len))
    }

    private fun orderList(items: MutableList<Item>) : List<Item> {
        val newList : MutableList<Item> = mutableListOf()

        if (items.isNullOrEmpty()){
            return items
        }
        for(i in items.indices){
            val m: Matcher = Pattern.compile("\\((.*?)\\)").matcher(items[i].name)
            try {
                if (m.find() && Integer.parseInt(m.group(1))>2005){
                    items[i].year = Integer.parseInt(m.group(1))
                    newList.add(items[i])
                }
                } catch (e: NumberFormatException) {
                    println("Input String cannot be parsed to Integer.")
                }
        }
        return newList.sortedByDescending { it.year }
    }
}