package com.cricketsports.dafamobile

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cricketsports.dafamobile.adapter.RVAdapter
import com.cricketsports.dafamobile.api.RetrofitInterface
import com.cricketsports.dafamobile.datamodel.DataModel
import com.cricketsports.dafamobile.extensions.OnSwipeTouchListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    var baseUrl = "https://api.cricapi.com/"
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.scheduleLayoutAnimation()

        getShedule()

        recyclerView.setOnTouchListener(object : OnSwipeTouchListener() {
            override fun onSwipeRight() {
                recyclerView.removeAllViewsInLayout()
                getShedule()
            }

            override fun onSwipeLeft() {
                recyclerView.removeAllViewsInLayout()
                getShedule()
            }
        })

    }

    fun getShedule() {
        val api = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitInterface::class.java)

        api.getNews().enqueue(object: Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.body() != null) {
                    adapter = RVAdapter(this@MainActivity, response.body()!!.`data`)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                    recyclerView.scheduleLayoutAnimation()
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Toast
                    .makeText(applicationContext, "Something went wrong. Try to check your internet connection.", Toast.LENGTH_LONG)
                    .show()
                Log.d("devx", t.toString())
            }
        })
    }


}