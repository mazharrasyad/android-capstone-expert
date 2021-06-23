package com.muhazharrasyad.capstoneexpertii.di

import com.muhazharrasyad.capstoneexpertii.core.domain.usecase.SportInteractor
import com.muhazharrasyad.capstoneexpertii.core.domain.usecase.SportUseCase
import com.muhazharrasyad.capstoneexpertii.detail.DetailSportViewModel
import com.muhazharrasyad.capstoneexpertii.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<SportUseCase> { SportInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailSportViewModel(get()) }
}