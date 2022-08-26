package com.example.restaurantssearch.data.model

import java.io.Serializable

data class MenusResponse(
    val menus: ArrayList<MenusItem?>? = null
):Serializable

data class MenusItem(
    val categories: ArrayList<CategoriesItem?>? = null,
    val restaurantId: Int? = null
)

data class MenuItemsItem(
    val images: String? = null,
    val price: String? = null,
    val name: String? = null,
    val description: String? = null,
    val id: String? = null
)

data class CategoriesItem(
    val menu_items: ArrayList<MenuItemsItem?>? = null,
    val name: String? = null,
    val id: String? = null
)