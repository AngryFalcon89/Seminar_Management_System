package com.example.seminarmanagementsystem.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.AppEntryUseCases
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.ReadAppEntry
import com.example.seminarmanagementsystem.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {
    var splashCondition by mutableStateOf(true)

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)

    init{
        appEntryUseCases.readAppEntry.invoke().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                startDestination = Route.BookNavigation.route
            }else{
                startDestination = Route.AppStartNavigation.route
            }
            delay(100)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}