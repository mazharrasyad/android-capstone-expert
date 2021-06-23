package com.muhazharrasyad.capstoneexpertii.detail

import androidx.lifecycle.ViewModel
import com.muhazharrasyad.capstoneexpertii.core.domain.model.Sport
import com.muhazharrasyad.capstoneexpertii.core.domain.usecase.SportUseCase

class DetailSportViewModel(private val sportUseCase: SportUseCase) : ViewModel() {
    fun setFavoriteSport(sport: Sport, newStatus:Boolean) =
        sportUseCase.setFavoriteSport(sport, newStatus)
}

