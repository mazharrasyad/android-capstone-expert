package com.muhazharrasyad.capstoneexpertii.core.domain.repository

import com.muhazharrasyad.capstoneexpertii.core.data.Resource
import com.muhazharrasyad.capstoneexpertii.core.domain.model.Sport
import kotlinx.coroutines.flow.Flow

interface ISportRepository {

    fun getAllSport(): Flow<Resource<List<Sport>>>

    fun getFavoriteSport(): Flow<List<Sport>>

    fun setFavoriteSport(sport: Sport, state: Boolean)

}