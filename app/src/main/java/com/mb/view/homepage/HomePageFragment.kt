package com.mb.view.homepage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
    private val mainList : MutableList<Results> = mutableListOf()
    private val adapter = HomePageAdapter()
    private var offset = 0
    private var sortToast: Toast? = null
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

        scrollListener()
        getCharacters(offset)
    }

    private fun getCharacters(offset : Int) {
        viewModel.getAllCharacters(offset).observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.SUCCESS -> onSucces(it.data?.data?.results)
            }
        })
    }

    private fun onSucces(characterList: List<Results>?) {
        sortToast?.cancel()

        mainList.addAll(characterList as MutableList<Results>)
        viewModel.characterList = mainList
        adapter.submitList(viewModel.characterList)
        Log.i("deneme",characterList?.get(0)!!.thumbnail.path)
    }

    private fun scrollListener() {
        binding.characterListRec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN) && recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    offset +=30
                    sortToast = Toast.makeText(requireContext(),"Loading..",Toast.LENGTH_SHORT)
                    val recyclerViewState = recyclerView.layoutManager?.onSaveInstanceState();
                    getCharacters(offset)
                    recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
                }
            }
        })
    }
}