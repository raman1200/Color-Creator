package com.raman.project.colorcreator.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raman.project.colorcreator.models.ColorData
import com.raman.project.colorcreator.repositories.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ColorViewModel @Inject constructor(private val repository: ColorRepository) : ViewModel() {

    val colorData: StateFlow<List<ColorData>>
        get() = repository.getColorData

    val unsyncedValue: StateFlow<Int>
        get() = repository._unsyncedValue

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllColorData()
            repository.getUnsyncedColors()
        }

    }
    fun syncData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.syncData()
        }
    }

    fun insertColor() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDataInRoom()
        }
    }




}