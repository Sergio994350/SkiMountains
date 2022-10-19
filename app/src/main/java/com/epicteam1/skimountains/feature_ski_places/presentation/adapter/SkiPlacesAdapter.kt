package com.epicteam1.skimountains.feature_ski_places.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.epicteam1.skimountains.R
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import kotlinx.android.synthetic.main.item_ski_place.view.*


class SkiPlacesAdapter : RecyclerView.Adapter<SkiPlacesAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item.rootView)

    private val differCallback = object : DiffUtil.ItemCallback<SkiPlace>() {
        override fun areItemsTheSame(oldItem: SkiPlace, newItem: SkiPlace): Boolean {
            return oldItem.skiPlaceId == newItem.skiPlaceId
        }

        override fun areContentsTheSame(oldItem: SkiPlace, newItem: SkiPlace): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ski_place, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(place.mainPic).into(image_view_item_ski_place)
            name_ski_place.text = "${place.nameRus} ${place.regionRus}"
            card_save.setOnClickListener {
                onItemClickListener2?.let { it(place) }
                card_save.setCardBackgroundColor(Color.WHITE);
            }
            setOnClickListener {
                onItemClickListener?.let { it(place) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((SkiPlace) -> Unit)? = null
    private var onItemClickListener2: ((SkiPlace) -> Unit)? = null
    fun setOnItemClickListener(listener: (SkiPlace) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemClickListener2(listener: (SkiPlace) -> Unit) {
        onItemClickListener2 = listener
    }

}