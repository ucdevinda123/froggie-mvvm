package com.froggie.design.repository.data.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DesignCategory(@PrimaryKey  var id: Int,var status: Boolean)
