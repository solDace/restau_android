package fr.isen.sorroche.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.sorroche.androiderestaurant.model.Plats
import fr.isen.sorroche.androidrestaurant.model.Items

class OrderAdapter (var dataSet: List<Plats>) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name_plat_order)
        val quality: TextView = view.findViewById(R.id.quantity_plat_order)
        val price: TextView = view.findViewById(R.id.tot_price_plat_order)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: OrderAdapter.ViewHolder, position: Int) {

        viewHolder.name.text = dataSet[position].plat.nameFr
        viewHolder.price.text = (dataSet[position].nb_plat * dataSet[position].plat.prices[0].price!!.toFloat()).toString()
        viewHolder.quality.text=dataSet[position].nb_plat.toString()
    }

    override fun getItemCount()= dataSet.size

    fun refreshList(contenu_panier: List<Plats>){
        dataSet = contenu_panier
        notifyDataSetChanged()
    }

}