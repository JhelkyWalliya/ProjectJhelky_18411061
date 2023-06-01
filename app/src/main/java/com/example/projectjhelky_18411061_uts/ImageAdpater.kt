package com.example.projectjhelky_18411061_uts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageAdpater(var mContext: Context, var wisataList:List<Image>):
    RecyclerView.Adapter<ImageAdpater.ListViewHolder>()
{
    inner class ListViewHolder(var v: View): RecyclerView.ViewHolder(v){
      var imageSrc = v.findViewById<ImageView>(R.id._image)
        var title = v.findViewById<TextView>(R.id._title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder{
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.item_image,parent,false)
        return ListViewHolder(v)
    }

    override fun getItemCount(): Int = wisataList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var newList = wisataList[position]
       holder.imageSrc.loadImage(newList.imageSrc)
        holder.title.text = newList.imageTitle
        holder.v.setOnClickListener{

            val imageSrc = newList.imageSrc
            val imageTitle = newList.imageTitle
            val imageDesc = newList.imageDesc

            val mIntent = Intent(mContext, DetailActivity::class.java)
            mIntent.putExtra("IMAGESRC",imageSrc)
            mIntent.putExtra("IMAGETITLE",imageTitle)
            mIntent.putExtra("IMAGEDESC", imageDesc)
            mContext.startActivity(mIntent)
        }

    }
}