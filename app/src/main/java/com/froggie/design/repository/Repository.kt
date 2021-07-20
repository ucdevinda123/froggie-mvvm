package com.froggie.design.repository

import androidx.room.withTransaction
import com.froggie.design.repository.api.DesignAPI
import com.froggie.design.repository.data.Result
import com.froggie.design.repository.offline.db.DesignDatabase
import com.froggie.design.repository.data.category.DesignCategory
import com.froggie.design.repository.util.Category
import com.froggie.design.repository.util.networkBoundResource
import javax.inject.Inject

class Repository @Inject constructor(
    private val designAPI: DesignAPI,
    private val designDb: DesignDatabase
) {
    private val designDao = designDb.designDao()

    suspend fun insertCategorySelection(categoryId: Int, status : Boolean){
        designDao.deleteAllCategoryStatus()
        designDao.insertCategoryStatus(DesignCategory(categoryId,status))
    }

    fun getCategoryStatus() = designDao.getDesignCategoryStatus(status = true)

    fun getFeaturedAnimations(categoryId: Int = Category.POPULAR.value) = networkBoundResource(
        query = {
            designDao.getDesignResultsByCategoryId(categoryId)
        },
        fetch = {
            designAPI.getFeaturedAnimations()
        },
        saveFetchedResult = { designsData ->
            designDb.withTransaction {
                designDao.deleteByCategoryId(categoryId)
                var designList: List<Result> = designsData.data.featured.results
                designDao.insertResults(designList)
                designList.forEach { design ->
                    run {
                        design.categoryId = categoryId
                        designDao.insertResult(design)
                    }
                }
            }
        }
    )

    fun getPopularAnimations(categoryId: Int = Category.FEATURED.value) = networkBoundResource(
        query = {
            designDao.getDesignResultsByCategoryId(categoryId)
        },
        fetch = {
            designAPI.getPopularAnimations()
        },
        saveFetchedResult = { designsData ->
            designDb.withTransaction {
                designDao.deleteByCategoryId(categoryId)
                var designList: List<Result> = designsData.data.popular.results
                designDao.insertResults(designList)
                designList.forEach { design ->
                    run {
                        design.categoryId = categoryId
                        designDao.insertResult(design)
                    }
                }
            }
        }
    )

    fun getRecentAnimations(categoryId: Int = Category.RECENT.value) = networkBoundResource(
        query = {
            designDao.getDesignResultsByCategoryId(categoryId)
        },
        fetch = {
            designAPI.getRecentAnimations()
        },
        saveFetchedResult = { designsData ->
            designDb.withTransaction {
                designDao.deleteByCategoryId(categoryId)
                var designList: List<Result> = designsData.data.recent.results
                designDao.insertResults(designList)
                designList.forEach { design ->
                    run {
                        design.categoryId = categoryId
                        designDao.insertResult(design)
                    }
                }
            }
        }
    )
}