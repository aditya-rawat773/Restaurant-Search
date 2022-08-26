package com.example.restaurantssearch.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantssearch.data.model.MenusResponse
import com.example.restaurantssearch.data.model.RestaurantsResponse
import com.example.restaurantssearch.data.repository.RestaurantsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val restaurantsRepo: RestaurantsRepo) :
    ViewModel() {

    private var _postRestaurants = MutableLiveData<RestaurantsResponse?>()
    val postRestaurants: LiveData<RestaurantsResponse?> = _postRestaurants

    private var _postMenus = MutableLiveData<MenusResponse?>()
    val postMenus: LiveData<MenusResponse?> = _postMenus

    fun getRestaurants(context: Context) {
        viewModelScope.launch {
            val response = restaurantsRepo.getRestaurants(context)
            _postRestaurants.postValue(response)
        }
    }

    fun getMenus(context: Context){
        viewModelScope.launch {
            val response = restaurantsRepo.getMenus(context)
            _postMenus.postValue(response)
        }
    }
}