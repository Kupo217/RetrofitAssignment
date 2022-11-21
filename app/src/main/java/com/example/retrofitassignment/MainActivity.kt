package com.example.retrofitassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitassignment.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataAdapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getUsers()
            }catch (e: IOException){
                Log.e(TAG, "IOException")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "HttpException")
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null)
            {
                dataAdapter.data = response.body()!!
            }else
            {
                Log.e(TAG, "Failed")
            }
        }
    }

    private fun setupRecyclerView() = binding.rvUsersList.apply {
        dataAdapter = DataAdapter()
        adapter = dataAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}