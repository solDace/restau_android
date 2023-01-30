package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.sorroche.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.sorroche.androiderestaurant.model.Panier
import fr.isen.sorroche.androiderestaurant.model.Plats
import fr.isen.sorroche.androidrestaurant.model.Items
import java.io.File


class DetailActivity : AppCompatActivity() {

    private var quantity: Int = 0
    private lateinit var currentDish: Items
    private lateinit var panier: Panier
    private lateinit var binding: ActivityDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentDish = intent.getSerializableExtra("item") as Items
        panier= Panier(mutableListOf<Plats>())

        binding.caroussel.adapter = DetailAdapter(this,currentDish.images)

        binding.textDetail.text = currentDish.nameFr
        binding.ingredient.text = currentDish.ingredients?.joinToString(", ") { it.nameFr.toString() }
        binding.achatDetail.text = currentDish.prices[0].price.toString()+ "€"
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
        binding.achatDetail.text = (currentDish.prices[0].price?.times(quantity)).toString() +"€"
        binding.quantity.text = quantity.toString()
    }

    fun ajoutPanier(){
        if(File(cacheDir.absolutePath,"panier_courant.json").exists()){
            val file = File(cacheDir.absolutePath,"panier_courant.json")
            val jsonString = file.readText()
            val gson=Gson()
            val tabPanierEnregistre = gson.fromJson(jsonString,Panier::class.java)
            if(tabPanierEnregistre.panierObjectList.any { it.plat.id == currentDish.id  }){
                //tabPanierEnregistre.panierObjectList.replaceAll {  }
            }else{
                //Log.i("panier", panier.panierObjectList.toString())
                panier.panierObjectList.add(Plats(currentDish,quantity))
                //Log.i("CART_CONTAINER", panier.toString())
                Snackbar.make(binding.root, "Plat ajouté au panier "+quantity+" "+currentDish.nameFr, Snackbar.LENGTH_SHORT).show()
            }
        }
        val jsonBuilder= GsonBuilder().setPrettyPrinting().create()
        val jsonText = jsonBuilder.toJson(panier)
        File(cacheDir.absolutePath,"panier_courant.json").writeText(jsonText)
    }

}