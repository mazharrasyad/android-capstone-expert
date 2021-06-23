package com.muhazharrasyad.capstoneexpertii.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    val idSport: String,
    val strSport: String,
    val strFormat: String,
    val strSportThumb: String,
    val strSportThumbGreen: String,
    val strSportDescription: String,
    val isFavorite: Boolean = false
) : Parcelable