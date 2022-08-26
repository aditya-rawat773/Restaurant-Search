package com.example.restaurantssearch.data.model

data class RestaurantsResponse(
    val restaurants: ArrayList<RestaurantsItem?>? = null
)

data class Latlng(
    val lng: Double? = null,
    val lat: Double? = null
)

data class RestaurantsItem(
    val photograph: String? = null,
    val address: String? = null,
    val reviews: List<ReviewsItem?>? = null,
    val operatingHours: OperatingHours? = null,
    val name: String? = null,
    val id: Int? = null,
    val neighborhood: String? = null,
    val cuisineType: String? = null,
    val latlng: Latlng? = null
)

data class OperatingHours(
    val monday: String? = null,
    val thursday: String? = null,
    val friday: String? = null,
    val sunday: String? = null,
    val wednesday: String? = null,
    val tuesday: String? = null,
    val saturday: String? = null
)

data class ReviewsItem(
    val date: String? = null,
    val comments: String? = null,
    val name: String? = null,
    val rating: Int? = null
)