package com.luc.base.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "note_table")
class Note { //: BaseModel()
    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String = ""
    @SerializedName("content")
    var content: String? = null
}