package com.froggie.design.repository.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CreatedBy(@ColumnInfo(name = "creatorUser") val name: String, val avatarUrl: String) : Parcelable