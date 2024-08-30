package com.raman.project.colorcreator.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.raman.project.colorcreator.db.ColorDao
import com.raman.project.colorcreator.models.ColorData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await

import javax.inject.Inject
import kotlin.random.Random

class ColorRepository @Inject constructor(private val colorDao: ColorDao){

    private val _getColorData = MutableStateFlow<List<ColorData>>(emptyList())
    val getColorData : StateFlow<List<ColorData>>
        get() = _getColorData

    val _unsyncedValue = MutableStateFlow<Int>(0)

    suspend fun insertDataInRoom() {
        val colorData = ColorData(0, generateColor(), getTimeStamp())
        colorDao.insert(colorData)
        getAllColorData()
        getUnsyncedColors()
    }

    suspend fun syncData() {
        val unsyncedColors = colorDao.getUnsyncedColors()
        val firestore = FirebaseFirestore.getInstance()
        unsyncedColors.forEach {
            try {
                firestore.collection("Colors").document().set(it).await()
                colorDao.update(it.copy(isSynced = true))
                getUnsyncedColors()
            }
            catch (e:Exception){
                Log.e("Firebase Error", e.localizedMessage)
            }



        }




    }




    suspend fun getUnsyncedColors() {
        _unsyncedValue.emit(colorDao.getUnsyncedColors().size)
    }

    suspend fun getAllColorData(){
        _getColorData.emit(colorDao.getColorData())
    }

    suspend fun markAsSynced(color: ColorData) {
        colorDao.update(color.copy(isSynced = true))
    }

    private fun generateColor(): String {
        val r = Random.nextInt(0, 256)
        val g = Random.nextInt(0, 256)
        val b = Random.nextInt(0, 256)
        return String.format("#%02X%02X%02X", r, g, b)
    }
    private fun getTimeStamp() = System.currentTimeMillis()

}