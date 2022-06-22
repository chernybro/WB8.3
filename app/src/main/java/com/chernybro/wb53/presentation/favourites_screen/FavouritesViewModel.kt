package com.chernybro.wb53.presentation.favourites_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chernybro.wb53.data.remote.favourites.FavouritesApi
import com.chernybro.wb53.data.repository.FavouritesRepository
import com.chernybro.wb53.domain.data.FavouriteCat
import com.chernybro.wb53.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    private val _cats: MutableLiveData<List<FavouriteCat>> = MutableLiveData()
    val cats: LiveData<List<FavouriteCat>> = _cats

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    init {
        getFavourites()
    }

    fun deleteFavourite(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = favouritesRepository.deleteFavourite(id)) {
                is Resource.Success -> getFavourites()
                is Resource.Error -> _message.postValue(response.message)
            }
        }
    }

    private fun getFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = favouritesRepository.getFavourites()) {
                is Resource.Success -> _cats.postValue(response.data)
                is Resource.Error -> _message.postValue(response.message)
            }
        }
    }
}