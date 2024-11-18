package com.example.restik_beta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.restik_beta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val mainViewModel = (application as MyApp).mainViewModel

        val buttonNavigate = findViewById<Button>(R.id.button_navigate)
        buttonNavigate.setOnClickListener {

            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}