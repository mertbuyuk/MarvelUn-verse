package com.mb.viewmodel.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mb.api.ApiRepository
import com.mb.api.MarvelApiService
import com.mb.model.Character
import com.mb.model.Results
import com.mb.utils.Constants
import com.mb.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.nio.charset.CharacterCodingException
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    var characterList : List<Results>? = null

    fun getAllCharacters(offset : Int) : LiveData<Resource<Character>> = apiRepository.getCharacters(
        Constants.API_KEY,
        Constants.timeStamp,
        Constants.hash(),
        Constants.limit,
        offset
    )
}