package com.muhazharrasyad.capstoneexpertii.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.muhazharrasyad.capstoneexpertii.core.domain.usecase.SportUseCase

class HomeViewModel(sportUseCase: SportUseCase) : ViewModel() {
    val sport = sportUseCase.getAllSport().asLiveData()
}

