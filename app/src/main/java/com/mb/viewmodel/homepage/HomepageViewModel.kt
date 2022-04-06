package com.mb.viewmodel.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mb.api.MarvelApiService
import com.mb.model.Character
import com.mb.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.nio.charset.CharacterCodingException
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(private val apiService: MarvelApiService) : ViewModel() {

    fun get(){
        viewModelScope.launch {
            apiService.getAllCharacters(
                Constants.API_KEY,
                Constants.timeStamp,
                Constants.hash()
            )
        }
    }
}