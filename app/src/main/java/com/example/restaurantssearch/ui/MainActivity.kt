package com.example.restaurantssearch.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantssearch.adapters.RestaurantAdapter
import com.example.restaurantssearch.data.model.MenuItemsItem
import com.example.restaurantssearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var menusList: ArrayList<MenuItemsItem?> = ArrayList()
    private lateinit var binding: ActivityMainBinding
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        observeViewModel()
        setUpViews()
    }

    private fun setUpViews() {
        restaurantAdapter = RestaurantAdapter()

        binding.apply {
            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    restaurantAdapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    restaurantAdapter.filter.filter(newText)
                    return false
                }
            })

            rvRestaurants.apply {
                adapter = restaurantAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.getRestaurants(applicationContext)
            viewModel.getMenus(applicationContext)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.postRestaurants.observe(this, Observer { restaurantsData ->
            lifecycleScope.launch {
                restaurantAdapter.getData(restaurantsData?.restaurants!!)
                restaurantAdapter.notifyDataSetChanged()
            }
        })
    }
}