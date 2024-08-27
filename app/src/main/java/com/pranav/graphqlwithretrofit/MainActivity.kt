package com.pranav.graphqlwithretrofit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.pranav.graphqlwithretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getData()
    }

    private fun getData(){
        CoroutineScope(Dispatchers.Main).launch {
            val paramObject = JsonObject()
            paramObject.addProperty("query", "query {\n" +
                    "  countries {\n" +
                    "    name\n" +
                    "    phone\n" +
                    "    currency\n" +
                    "  }\n" +
                    "}")
            val retrofitService = RetrofitService.getInstance()
            val retrofitResponse = retrofitService.getCountries(paramObject)
            println(" ==== $retrofitResponse")
            val countryList = retrofitResponse.body()?.data?.countries
            val countryAdapter = CountryAdapter(countryList)
            binding.countryRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.countryRecyclerView.adapter = countryAdapter
        }
    }

}
