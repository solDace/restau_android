package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.sorroche.androiderestaurant.databinding.ActivityNourituresBinding

class NouritureActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNourituresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNourituresBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_nouritures)

        val title = findViewById<TextView>(R.id.titre)
        title.text = intent.getStringExtra("title")

    }


}