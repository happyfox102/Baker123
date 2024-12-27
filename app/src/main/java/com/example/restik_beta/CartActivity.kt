package com.example.restik_beta

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://192.168.31.144:5000" // Замените на ваш URL

    val apiService: Repository.ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Repository.ApiService::class.java)
    }
}

class CartActivity : AppCompatActivity() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var checkoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val recyclerView = findViewById<RecyclerView>(R.id.cart_recycler_view)
        val totalPriceText = findViewById<TextView>(R.id.total_price_text)
        checkoutButton = findViewById(R.id.checkout_button)

        val itemsInCart = Cart.getItems()
        cartAdapter = CartAdapter(this, itemsInCart)
        recyclerView.adapter = cartAdapter

        val totalPrice = Cart.getTotalPrice()
        totalPriceText.text = "Итоговая сумма заказа: $totalPrice ₽"

        checkoutButton.setOnClickListener {
            val table_number = findViewById<EditText>(R.id.et_table_number).text.toString().toInt()
            val paymanet_method_id = findViewById<RadioGroup>(R.id.payment_method_group).checkedRadioButtonId
            val payment_method = when(paymanet_method_id){
                R.id.payment_card -> 1
                R.id.payment_cash -> 2
                else -> 0
            }
            val orderData = OrderData(1, table_number, payment_method, itemsInCart)


            RetrofitClient.apiService.createOrder(orderData)
                .enqueue(object : Callback<ResponseData> {
                    override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                        if (response.isSuccessful) {
                            Toast.makeText(applicationContext, "Заказ оформлен", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(applicationContext, "Ошибка оформления заказа", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                        Toast.makeText(applicationContext, "Ошибка сети", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    override fun onResume() {
        super.onResume()
        val updatedItems = Cart.getItems()
        Log.d("Cart", "Updated items in cart: $updatedItems")

        cartAdapter = CartAdapter(this, updatedItems)
        val recyclerView = findViewById<RecyclerView>(R.id.cart_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartAdapter
        cartAdapter.notifyDataSetChanged()
    }
}

class ResponseData {

}
