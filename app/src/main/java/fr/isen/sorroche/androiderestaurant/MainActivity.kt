package fr.isen.sorroche.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val action = findViewById<Button>(R.id.testbutton)
        action.setOnClickListener{

            Snackbar.make(it, "Hello World", Snackbar.LENGTH_SHORT).show()
        }
    }
}