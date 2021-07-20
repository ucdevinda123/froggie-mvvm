package com.froggie.design.viewmodel

import androidx.lifecycle.ViewModel
import com.froggie.design.repository.Repository
import com.froggie.design.repository.data.Result
import com.froggie.design.repository.data.category.DesignCategory
import com.froggie.design.repository.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    fun getPopularAnimations(): Flow<Resource<List<Result>>> = repository.getPopularAnimations()

    fun getFeaturedAnimations(): Flow<Resource<List<Result>>> = repository.getFeaturedAnimations()

    fun getRecentAnimations(): Flow<Resource<List<Result>>> = repository.getRecentAnimations()

    suspend fun getCategoryStatus(): Flow<DesignCategory?> = repository.getCategoryStatus()

    suspend fun pickCategory(categoryId : Int, status : Boolean) : Unit = repository.insertCategorySelection(categoryId,status)

}