package com.froggie.design.repository.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.froggie.design.repository.data.Result
import com.froggie.design.repository.data.category.DesignCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(results: List<Result>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(results: Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoryStatus(category: DesignCategory)

    @Query("SELECT * FROM results where categoryId=:categoryId")
    fun getDesignResultsByCategoryId(categoryId: Int): Flow<List<Result>>

    @Query("SELECT * FROM designCategory where status =:status")
    fun getDesignCategoryStatus(status: Boolean): Flow<DesignCategory>

    @Query("DELETE FROM results")
    suspend fun deleteAll()

    @Query("DELETE FROM designcategory")
    suspend fun deleteAllCategoryStatus()

    @Query("DELETE FROM results where categoryId=:categoryId")
    suspend fun deleteByCategoryId(categoryId: Int)
}