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

    private var quantity: Int = 0
    private lateinit var it: Items
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        it = intent.getSerializableExtra("item") as Items

        val firstImage = it.images[0]
        if (firstImage.isNotEmpty()) {
            Picasso.get().load(firstImage).resize(850,550).into(binding.imageDetail)
        }

        binding.textDetail.text = it.nameFr
        binding.ingredient.text = it.ingredients?.joinToString(", ") { it.nameFr.toString() }
        binding.achatDetail.text = it.prices[0].price.toString()+ "€"
        binding.less.setOnClickListener {
            if (binding.quantity.text.toString().toInt() > 0) {
                quantity--
                changePrice()
            }
        }
        binding.more.setOnClickListener {
            quantity++
            changePrice()
        }
    }

    fun changePrice(){
        binding.achatDetail.text = ((it.prices[0].price?.toInt() ?: 0) * quantity).toString() +"€"
        binding.quantity.text = quantity.toString()
    }

}