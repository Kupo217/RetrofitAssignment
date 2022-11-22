package com.example.retrofitassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import retrofit2.HttpException
import java.io.IOException

class UserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val id = intent.getIntExtra("id", 0)

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getSingleUser(id)
            }catch (e: IOException){
                Log.e(TAG, "IOException")
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.e(TAG, "HttpException")
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null)
            {
                val email = response.body()!!.data.email
                val firstName = response.body()!!.data.first_name
                val lastName = response.body()!!.data.last_name

                findViewById<TextView>(R.id.tvEmail).text = email
                findViewById<TextView>(R.id.tvFirstName).text = firstName
                findViewById<TextView>(R.id.tvLastName).text = lastName
            }
        }
    }
}