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
import com.epicteam1.skimountains.feature_ski_places.core.getExtendedName
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import kotlinx.android.synthetic.main.item_ski_place.view.*


class SkiPlacesAdapter(
    private val onItemClickListener: (skiPlace: SkiPlace) -> Unit,
    private val onItemSaveClickListener: (skiPlace: SkiPlace) -> Unit
) : RecyclerView.Adapter<SkiPlacesAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item.rootView)

    private val differCallback = object : DiffUtil.ItemCallback<SkiPlace>() {
        override fun areItemsTheSame(oldItem: SkiPlace, newItem: SkiPlace): Boolean {
            return oldItem.skiPlaceId == newItem.skiPlaceId
        }

        override fun areContentsTheSame(oldItem: SkiPlace, newItem: SkiPlace): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    @SuppressLint("NotifyDataSetChanged")
    fun submitSkiPlacesList(skiPlaces: List<SkiPlace>) {
        differ.submitList(skiPlaces)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ski_place, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = differ.currentList[position]
        val skiPlaceExtendedName = place.getExtendedName()
        holder.itemView.apply {
            Glide.with(this).load(place.mainPic).into(image_view_item_ski_place)
            name_ski_place.text = skiPlaceExtendedName
            card_save.setOnClickListener {
                onItemSaveClickListener(place)
                card_save.setCardBackgroundColor(Color.WHITE)
            }
            setOnClickListener {
                onItemClickListener(place)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}