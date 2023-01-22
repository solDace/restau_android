package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import fr.isen.sorroche.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.sorroche.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.sorroche.androidrestaurant.model.Items
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    private lateinit var dish : Items
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val it:Items = intent.getSerializableExtra("item") as Items

        val firstImage = it.images[0]
        if (firstImage.isNotEmpty()) {
            Picasso.get().load(firstImage).into(binding.imageDetail)
        }

        binding.textDetail.text = it.nameFr
        binding.ingredient.text = it.ingredients?.joinToString(", ") { it.nameFr.toString() }



    }

}