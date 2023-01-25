package fr.isen.sorroche.androiderestaurant

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.sorroche.androidrestaurant.model.Items

class OrderAdapter (var dataSet: ArrayList<Items>, val onItemClickListener: (Items) -> Unit) :
    RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount()= dataSet.size

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}