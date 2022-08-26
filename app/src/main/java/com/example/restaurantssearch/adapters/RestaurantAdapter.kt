package com.example.restaurantssearch.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantssearch.data.model.RestaurantsItem
import com.example.restaurantssearch.databinding.ItemRestaurantBinding
import java.util.*
import kotlin.collections.ArrayList

class RestaurantAdapter() : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>(),
    Filterable {
    private var mList: ArrayList<RestaurantsItem?> = ArrayList()
    private var mFilteredList: ArrayList<RestaurantsItem?> = ArrayList()

    fun getData(list: ArrayList<RestaurantsItem?>) {
        mList = list
        mFilteredList = mList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return (RestaurantViewHolder(binding))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        mFilteredList[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    class RestaurantViewHolder(private val binding: ItemRestaurantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurant: RestaurantsItem) {
            binding.apply {
                titleName.text = restaurant.name
                titleCategory.text = restaurant.cuisineType
                titlePlace.text = restaurant.neighborhood
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                mFilteredList = if (charString.isEmpty()) mList else {
                    val filteredList = ArrayList<RestaurantsItem?>()
                    mList
                        .filter {
                            (it!!.cuisineType!!.lowercase(Locale.getDefault())
                                .contains(constraint!!)) or
                                    (it.name!!.lowercase(Locale.getDefault()).contains(constraint))
                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = mFilteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                mFilteredList = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<RestaurantsItem?>
                notifyDataSetChanged()

            }
        }
    }

}