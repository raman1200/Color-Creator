package com.raman.project.colorcreator.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "color_details")
data class ColorData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val id: Int,
    @ColumnInfo val value: String,
    @ColumnInfo val time: Long,
    @ColumnInfo var isSynced: Boolean = false
)
