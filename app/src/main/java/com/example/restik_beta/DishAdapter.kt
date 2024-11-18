package com.example.restik_beta

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DishAdapter(private val dishes: List<Dish>) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishImage: ImageView = itemView.findViewById(R.id.dish_image)
        val dishName: TextView = itemView.findViewById(R.id.dish_name)
        val dishPrice: TextView = itemView.findViewById(R.id.dish_price)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)

        fun bind(dish: Dish) {
            dishName.text = dish.name
            dishPrice.text = "${dish.price} ₽"
            addToCartButton.setOnClickListener {
                Cart.addItem(dish)
                Toast.makeText(itemView.context, "${dish.name} добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        Log.d("DishAdapter", "Creating new ViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        Log.d("DishAdapter", "Binding dish: ${dish.name}")
        holder.dishName.text = dish.name
        holder.dishPrice.text = dish.price.toString()
        holder.bind(dishes[position])
        holder.addToCartButton.setOnClickListener {
            Cart.addItem(dish)
            Toast.makeText(
                holder.itemView.context,
                "${dish.name} добавлен в корзину",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.bind(dish)
        Glide.with(holder.itemView.context).load(dish.imageUrl).into(holder.dishImage)

    }

    override fun getItemCount(): Int {
        return dishes.size
    }
}
