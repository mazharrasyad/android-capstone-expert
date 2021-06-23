package com.muhazharrasyad.capstoneexpertii.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muhazharrasyad.capstoneexpertii.core.domain.usecase.SportUseCase

class FavoriteViewModel(sportUseCase: SportUseCase) : ViewModel() {
    val favoriteSport = sportUseCase.getFavoriteSport().asLiveData()
}

