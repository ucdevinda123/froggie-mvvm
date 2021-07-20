package com.froggie.design.repository.offline.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.froggie.design.repository.data.Result
import com.froggie.design.repository.data.category.DesignCategory
import com.froggie.design.repository.offline.ResultDao

@Database(entities = [Result::class, DesignCategory::class],version = 2,exportSchema = false)
abstract class DesignDatabase : RoomDatabase() {
    abstract fun designDao() : ResultDao
}