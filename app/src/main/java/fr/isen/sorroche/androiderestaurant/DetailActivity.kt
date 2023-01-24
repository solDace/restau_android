package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import fr.isen.sorroche.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.sorroche.androiderestaurant.model.Panier
import fr.isen.sorroche.androiderestaurant.model.Plats
import fr.isen.sorroche.androidrestaurant.model.Items



class DetailActivity : AppCompatActivity() {

    private var quantity: Int = 0
    private lateinit var it: Items
    private lateinit var panier: Panier
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        it = intent.getSerializableExtra("item") as Items
        panier= Panier(mutableListOf<Plats>())

        binding.caroussel.adapter = DetailAdapter(this,it.images)

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

        binding.achatDetail.setOnClickListener{
            if (quantity>0){
                ajoutPanier()
            }
        }
    }

    fun changePrice(){
        binding.achatDetail.text = ((it.prices[0].price?.toInt() ?: 0) * quantity).toString() +"€"
        binding.quantity.text = quantity.toString()
    }

    fun ajoutPanier(){
        panier.panierObjectList.add(Plats(it,quantity))
        Log.i("CART_CONTAINER", panier.toString())
        Snackbar.make(binding.root, "Plat ajouté au panier "+quantity+" "+it.nameFr, Snackbar.LENGTH_SHORT).show()
    }

}