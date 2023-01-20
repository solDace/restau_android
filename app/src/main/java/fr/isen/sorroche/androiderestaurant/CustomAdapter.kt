package fr.isen.sorroche.androiderestaurant

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.sorroche.androidrestaurant.model.Items


class CustomAdapter(var dataSet: ArrayList<Items>, val onItemClickListener: () -> Unit) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val image :ImageView = view.findViewById(R.id.imageview)
        val prices :TableLayout = view.findViewById(R.id.prices)
         fun setPrices(elem: Items) {
            elem.prices.forEach { price ->
                val row = TableRow(prices.context)

                val size = TextView(prices?.context)
                size.textSize = 12f
                row.addView(size)

                val prix = TextView(prices?.context)
                prix.gravity = Gravity.END
                prix.textSize = 12f
                prix.text = price.price.toString()+ "€"
                row.addView(prix)

                prices?.addView(row)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].nameFr
        viewHolder.setPrices(dataSet[position])

        val firstImage = dataSet[position].images[0]
        if (firstImage.isNotEmpty()) {
            Picasso.get().load(firstImage).resize(50, 50).into(viewHolder.image)
        }

        viewHolder.itemView.setOnClickListener{
            onItemClickListener()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun refreshList(dishesFromAPI: ArrayList<Items>){
        dataSet =dishesFromAPI
        notifyDataSetChanged()
    }


}
