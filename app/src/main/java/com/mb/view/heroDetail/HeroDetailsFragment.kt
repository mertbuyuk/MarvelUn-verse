package com.mb.view.heroDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.mb.marveluniverse.R
import com.mb.marveluniverse.databinding.FragmentHeroDetailsBinding
import com.mb.model.Results
import com.mb.view.comics.ComicAdapter
import com.mb.viewmodel.heroDetailViewModel.HeroDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        adapter.submitList(results[0].comics.items)
    }

}