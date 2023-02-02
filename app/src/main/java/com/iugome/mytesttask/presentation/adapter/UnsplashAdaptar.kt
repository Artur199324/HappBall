package com.iugome.mytesttask.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.iugome.mytesttask.R
import com.iugome.mytesttask.domain.models.UnsplashModel
import com.squareup.picasso.Picasso

var listener: UnsplashAdaptar.OnItemClickListener? = null
var positionn = 0
class UnsplashAdaptar(var context: Context, var arrayUnsplashMode: ArrayList<UnsplashModel>) :
    RecyclerView.Adapter<UnsplashAdaptar.MangoViewHolder>() {

    class MangoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagePoster: ImageView

        init {
            imagePoster = itemView.findViewById(R.id.imageUnsplash)

            itemView.setOnClickListener {
                if (listener != null) {
                    positionn = adapterPosition
                    if (adapterPosition !=RecyclerView.NO_POSITION){
                        listener!!.onItemClick(positionn)
                    }
                }
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MangoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.unsplash_item, parent, false)
        return MangoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MangoViewHolder, position: Int) {
        val unsplashModel = arrayUnsplashMode[position]
        Picasso.get().load(unsplashModel.linkPhoto).fit().centerInside().into(holder.imagePoster)
    }

    override fun getItemCount(): Int {
        return arrayUnsplashMode.size
    }
}