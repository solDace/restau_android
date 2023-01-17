package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.sorroche.androiderestaurant.databinding.ActivityNourituresBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNourituresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNourituresBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_nouritures)

        val title = findViewById<TextView>(R.id.titre)
        title.text = intent.getStringExtra("title")

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview_list_menu)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = resources.getStringArray(R.array.dessert)

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }



}