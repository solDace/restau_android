package fr.isen.sorroche.androiderestaurant



import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import fr.isen.sorroche.androiderestaurant.databinding.ActivityCategoryBinding


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
        binding.recyclerviewListMenu.adapter = CustomAdapter(dishes){
            val intent = Intent(this,DetailActivity::class.java)
            startActivity(intent)
        }

    }



}