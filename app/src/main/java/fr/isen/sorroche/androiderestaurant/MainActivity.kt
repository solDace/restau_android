package fr.isen.sorroche.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import fr.isen.sorroche.androiderestaurant.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.entrees.setOnClickListener{
            changePage(binding.entrees)
        }

        binding.plats.setOnClickListener{
            changePage(binding.plats)
        }

        binding.desserts.setOnClickListener{
            changePage(binding.desserts)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("onDestroy","page $this detruite")
    }

    fun changePage(Button: Button){
        val intent = Intent(this, NouritureActivity::class.java)
        intent.putExtra("title", Button.text)
        startActivity(intent)
    }
}