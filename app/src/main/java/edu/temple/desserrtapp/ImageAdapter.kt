package edu.temple.desserrtapp

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter (private val items : Array<Item>, private val clickEvent: (Item)->Unit) : RecyclerView.Adapter<ImageAdapter.ViewHolder>(){

    inner class ViewHolder(layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        // Fetch children from linear layout in the order they were added
        val imageView = layout.getChildAt(0) as ImageView
        val textView = layout.getChildAt(1) as TextView

        init {
            // Assign on click listener to entire layout
            layout.setOnClickListener{clickEvent(items[adapterPosition])}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Create a linearlayout in code. Note order in which children are added for later retrieval
        val linearLayout = LinearLayout(parent.context)
        with (linearLayout) {
            orientation = LinearLayout.VERTICAL
            addView(ImageView(parent.context).apply { layoutParams = ViewGroup.LayoutParams(300, 300) })
            addView(TextView(parent.context).apply { textSize = 22f })
        }

        return ViewHolder(linearLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(items[position].resourceId)
        holder.textView.text = items[position].description
    }

    override fun getItemCount(): Int {
        return items.size
    }

}