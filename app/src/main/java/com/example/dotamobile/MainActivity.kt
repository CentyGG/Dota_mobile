package com.example.dotamobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dotamobile.data.OpenDotaApi
import com.example.dotamobile.dataClasses.Hero
import com.example.dotamobile.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    private val _heroes = MutableStateFlow<List<Hero>>(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val retrofit = Retrofit.Builder().baseUrl("https://api.opendota.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(OpenDotaApi::class.java)
        binding.button.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val heroes = api.getHeroes()
                _heroes.value = heroes.toList()
                runOnUiThread{
                    binding.tv.text = _heroes.value[0].localized_name
                }
            }
        }
    }
}