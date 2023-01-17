package fr.isen.sorroche.androiderestaurant


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import fr.isen.sorroche.androiderestaurant.databinding.ActivityCategoryBinding
import org.json.JSONObject


class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val title = binding.titre
        title.text = intent.getStringExtra("title")

        val dishes = resources.getStringArray(R.array.plats).toList() as ArrayList<String>
        binding.recyclerviewListMenu.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewListMenu.adapter = CustomAdapter(arrayListOf()) {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
        loadDishesFromAPI()
    }
    private fun loadDishesFromAPI(){
        Volley.newRequestQueue(this)
        val url="http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            {
            Log.w("categoryActivity","response : $it")
        }, {
            Log.w("categoryActivity","erreur : $it")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

}