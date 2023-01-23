package fr.isen.sorroche.androiderestaurant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso

class DetailAdapter(private val mContext: Context, private val itemList: ArrayList<String>) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.image_item, container, false)
        var imageView: ImageView = view.findViewById(R.id.image_caroussel)
        val firstImage = itemList[position]
        if (firstImage.isNotEmpty()) {
            Picasso.get().load(itemList[position]).resize(850, 550).into(imageView)
        }
        container.addView(view, position)
        return view
    }
    override fun getCount(): Int {
        return itemList.size
    }
    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}