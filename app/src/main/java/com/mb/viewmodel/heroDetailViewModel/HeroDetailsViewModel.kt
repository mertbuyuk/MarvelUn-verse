package com.mb.viewmodel.heroDetailViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mb.api.ApiRepository
import com.mb.model.Character
import com.mb.utils.Constants
import com.mb.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroDetailsViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    fun getCharacterDetails(id : Int) : LiveData<Resource<Character>> = apiRepository.getCharacterDetails(
        Constants.API_KEY,
        Constants.timeStamp,
        Constants.hash(),
        id
    )
}