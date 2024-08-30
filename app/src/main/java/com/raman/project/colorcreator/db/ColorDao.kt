package com.raman.project.colorcreator.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.raman.project.colorcreator.models.ColorData

@Dao
interface ColorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: ColorData)

    @Update
    suspend fun update(data: ColorData)

    @Query("DELETE FROM color_details")
    suspend fun deleteAll()

    @Query("Select * from color_details")
    fun getColorData() : List<ColorData>

    @Query("SELECT * FROM color_details WHERE isSynced = 0")
    fun getUnsyncedColors(): List<ColorData>

}