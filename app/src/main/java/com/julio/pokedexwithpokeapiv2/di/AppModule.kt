package com.julio.pokedexwithpokeapiv2.di

import com.julio.pokedexwithpokeapiv2.repository.MainRepository
import com.julio.pokedexwithpokeapiv2.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {(mainRepository : MainRepository) -> MainViewModel(mainRepository)}

}