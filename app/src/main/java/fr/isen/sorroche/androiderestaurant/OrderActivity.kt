package fr.isen.sorroche.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.sorroche.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.sorroche.androiderestaurant.databinding.ActivityOrderBinding
import fr.isen.sorroche.androiderestaurant.model.Panier
import fr.isen.sorroche.androiderestaurant.model.Plats
import fr.isen.sorroche.androidrestaurant.model.Items
import java.io.File
import java.io.FileInputStream

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.payOrder.setOnClickListener {passOrder()}

        val file = File(cacheDir.absolutePath,"panier_courant.json")
        if (file.exists()){
            val inputStream = FileInputStream(file)
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            val jsonString = String(buffer)
            val gson=Gson()
            val tabPanierEnregistre = gson.fromJson(jsonString,Panier::class.java)
            val pEnregistre =tabPanierEnregistre.panierObjectList

            binding.recyclerViewPanier.layoutManager = LinearLayoutManager(this)
            binding.recyclerViewPanier.adapter = OrderAdapter(pEnregistre)
        }
    }


    fun passOrder(){
        var file = File(cacheDir.absolutePath,"panier_courant.json")
        if( file.exists())
        {
            Snackbar.make(binding.root, "la commande est partie !", Snackbar.LENGTH_SHORT).show()
            File(cacheDir.absolutePath,"panier_courant.json").delete()
        }
        else{
            Snackbar.make(binding.root, "la panier est vide", Snackbar.LENGTH_SHORT).show()
        }
    }
}