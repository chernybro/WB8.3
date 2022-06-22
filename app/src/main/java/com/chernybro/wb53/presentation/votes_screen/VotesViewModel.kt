package com.chernybro.wb53.presentation.votes_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chernybro.wb53.data.remote.favourites.FavouritesApi
import com.chernybro.wb53.data.remote.search.SearchService
import com.chernybro.wb53.data.repository.FavouritesRepository
import com.chernybro.wb53.domain.data.Cat
import com.chernybro.wb53.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VotesViewModel @Inject constructor(
    private val searchService: SearchService,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    private val _cat: MutableLiveData<Cat> = MutableLiveData()
    val cat: LiveData<Cat> = _cat

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    init {
        getCat()
    }

    fun getCat() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = searchService.getCat()) {
                is Resource.Success -> _cat.postValue(response.data)
                is Resource.Error -> _message.postValue(response.message)
            }
        }
    }

    fun toFavourite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = favouritesRepository.insertFavourite(id)) {
                is Resource.Success -> {
                    getCat()
                    favouritesRepository.updateRemote()
                }
                is Resource.Error -> _message.postValue(response.message)
            }
        }

    }
}