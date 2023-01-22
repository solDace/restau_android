package fr.isen.sorroche.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.sorroche.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.sorroche.androidrestaurant.model.DataResult
import fr.isen.sorroche.androidrestaurant.model.Items
import org.json.JSONObject


class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val title = binding.titre
        title.text = intent.getStringExtra("title")

        binding.recyclerviewListMenu.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewListMenu.adapter = CustomAdapter(arrayListOf()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            startActivity(intent)
        }
        loadDishesFromAPI()
    }

    private fun loadDishesFromAPI() {

        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject, {
                    Log.w("CategoryActivity","erreur : $it")
                    handleAPIData(it.toString())
            }, Response.ErrorListener {
                Log.e("CategoryActivity", "erreur : $it")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    private fun handleAPIData(data: String){
        var dishesResult = Gson().fromJson(data, DataResult::class.java)
        val dishCategory=dishesResult.data.firstOrNull{it.nameFr == intent.getStringExtra("title") }

        val adapter = binding.recyclerviewListMenu.adapter as CustomAdapter
        adapter.refreshList(dishCategory?.items as ArrayList<Items>)
    }

}