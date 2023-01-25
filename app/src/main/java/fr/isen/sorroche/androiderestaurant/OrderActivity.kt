package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.sorroche.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.sorroche.androiderestaurant.databinding.ActivityOrderBinding
import java.io.File

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.payOrder.setOnClickListener {passOrder()}
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