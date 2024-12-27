package com.example.restik_beta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restik_beta.databinding.ActivityListBinding
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

data class Dish(
    val id: Int,
    val name: String,
    val price: Int,
    val imageUrl: String // assuming this exists in your response
)

data class OrderData(
    val quantity: Int,
    val table_number: Int,
    val payment_method: Int,
    val dishes: List<Dish>

)

object Cart : Serializable {
    private val items = mutableListOf<Dish>()

    fun addItem(dish: Dish) {
        items.add(dish)
    }

    fun removeItem(dish: Dish) {
        items.remove(dish)
    }

    fun getItems(): List<Dish> {
        return items
    }

    fun getTotalPrice(): Int {
        return items.sumOf { it.price }
    }
}

object RetrofitInstance {
    private const val BASE_URL = "http://192.168.31.144:5000"

    val api: Repository.ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Repository.ApiService::class.java)
    }
}

class ListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DishAdapter
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val buttonCart = findViewById<Button>(R.id.button_cart)
        buttonCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        fetchDishes()
    }

    private fun fetchDishes() {
        RetrofitInstance.api.getDishes().enqueue(object : Callback<List<Dish>> {
            override fun onResponse(call: Call<List<Dish>>, response: Response<List<Dish>>) {
                if (response.isSuccessful) {
                    val dishes = response.body() ?: emptyList()
                    adapter = DishAdapter(dishes)
                    recyclerView.adapter = adapter
                } else {
                    // Handle the error case
                }
            }

            override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                // Handle the failure case
            }
        })
    }
}