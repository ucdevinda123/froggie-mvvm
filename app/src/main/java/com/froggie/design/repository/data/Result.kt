package com.froggie.design.repository.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "results")
data class Result(
    @PrimaryKey
    val id: Int,
    val gifUrl: String?,
    val createdAt: String?,
    val bgColor: String?,
    val videoUrl: String?,
    @Embedded
    val createdBy: CreatedBy,
    val imageUrl: String,
    val name: String,
    val lottieUrl: String,
    var categoryId: Int
) : Parcelable