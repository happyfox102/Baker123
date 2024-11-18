package com.example.restik_beta

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restik_beta.databinding.ItemDishBinding

class CartAdapter(private val context: Context, private val items: List<Dish>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemDishBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val dish = items[position]
        holder.binding.dishName.text = dish.name
        holder.binding.dishPrice.text = "${dish.price} ₽"
        Glide.with(context).load(dish.imageUrl).into(holder.binding.dishImage)

        holder.binding.addToCartButton.visibility = View.GONE
    }

    override fun getItemCount(): Int = items.size

    inner class CartViewHolder(val binding: ItemDishBinding) : RecyclerView.ViewHolder(binding.root)
}
