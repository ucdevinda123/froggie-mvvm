package com.froggie.design.repository.api

import com.froggie.design.repository.data.FeaturedDesignResponse
import com.froggie.design.repository.data.PopularDesignResponse
import com.froggie.design.repository.data.RecentDesignResponse
import retrofit2.http.GET

interface DesignAPI {

    companion object {
        const val BASE_URL =
            "https://firebasestorage.googleapis.com/v0/b/lottiefiles-test.appspot.com/o/"
    }

    @GET("featuredAnimations.json?alt=media&token=f6e406f5-befb-40ab-a9b0-bb0a773b09fd")
    suspend fun getFeaturedAnimations(): FeaturedDesignResponse

    @GET("popularAnimations.json?alt=media&token=a32b4948-d278-4923-880e-8fb57741c190")
    suspend fun getPopularAnimations(): PopularDesignResponse

    @GET("recentAnimations.json?alt=media&token=f5acfd96-384a-4552-a0b5-399675a03826")
    suspend fun getRecentAnimations(): RecentDesignResponse
}