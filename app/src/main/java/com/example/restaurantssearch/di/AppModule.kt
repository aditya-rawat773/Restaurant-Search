package com.example.restaurantssearch.di

import com.example.restaurantssearch.data.repository.RestaurantsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestaurantRepository(): RestaurantsRepo = RestaurantsRepo()
}