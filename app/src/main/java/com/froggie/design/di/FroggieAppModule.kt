package com.froggie.design.di

import android.app.Application
import androidx.room.Room
import com.froggie.design.repository.api.DesignAPI
import com.froggie.design.repository.offline.db.DesignDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FroggieAppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(DesignAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitAPI(retrofit: Retrofit): DesignAPI {
        return retrofit.create(DesignAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): DesignDatabase =
        Room.databaseBuilder(app, DesignDatabase::class.java, "design_db")
            .build()
}