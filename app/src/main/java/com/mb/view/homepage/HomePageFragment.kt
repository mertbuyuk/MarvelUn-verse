package com.mb.view.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import com.mb.marveluniverse.R
import com.mb.marveluniverse.databinding.FragmentHomePageBinding
import com.mb.model.Character
import com.mb.model.Results
import com.mb.utils.Resource
import com.mb.viewmodel.homepage.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding  : FragmentHomePageBinding
    private val viewModel : HomepageViewModel by viewModels()
    private val adapter = HomePageAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterListRec.adapter = adapter
        getCharacters()

    }

    private fun getCharacters() {
        viewModel.getAllCharacters().observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCESS -> onSucces(it.data?.data?.results)
            }
        })
    }

    private fun onSucces(restaurantList: List<Results>?) {
        adapter.submitList(restaurantList)
        viewModel.characterList = restaurantList
        Log.i("deneme",restaurantList?.get(0)!!.thumbnail.path)
    }
}