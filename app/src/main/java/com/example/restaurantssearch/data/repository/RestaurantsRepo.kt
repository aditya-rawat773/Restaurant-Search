package com.example.restaurantssearch.data.repository

import android.content.Context
import com.example.restaurantssearch.data.model.MenusResponse
import com.example.restaurantssearch.data.model.RestaurantsResponse
import com.example.restaurantssearch.utils.JsonReader
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class RestaurantsRepo {

   suspend  fun getRestaurants(context: Context): RestaurantsResponse {
        val json = JsonReader().getJsonDataFromAsset(context, "restaurants.json")
        val listType = object : TypeToken<RestaurantsResponse>() {}.type
        return GsonBuilder().create().fromJson(json, listType)
    }

    suspend fun getMenus(context: Context): MenusResponse {
        val json = JsonReader().getJsonDataFromAsset(context, "menus.json")
        val listType = object : TypeToken<MenusResponse>() {}.type
        return GsonBuilder().create().fromJson(json, listType)
    }
}