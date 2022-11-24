package com.epicteam1.skimountains.feature_ski_places.presentation.adapter

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.item_save_ski_place.view.*

class SaveAdapter(
    private val onItemClickListener: (skiPlace: SkiPlace) -> Unit
) : RecyclerView.Adapter<SaveAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

    private val differCallback = object : DiffUtil.ItemCallback<SkiPlace>() {
        override fun areItemsTheSame(oldItem: SkiPlace, newItem: SkiPlace): Boolean {
            return oldItem.skiPlaceId == newItem.skiPlaceId
        }

        override fun areContentsTheSame(oldItem: SkiPlace, newItem: SkiPlace): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    fun getSkiPlace(position: Int): SkiPlace = differ.currentList[position]

    fun submitSavedSkiPlacesList(skiPlaces: List<SkiPlace>) {
        differ.submitList(skiPlaces)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_save_ski_place, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val save = differ.currentList[position]
        val skiPlaceExtendedName = save.getExtendedName()
        holder.itemView.apply {
            Glide.with(context).load(save.mainPic).into(image_view_item_saved_ski_place)
            name_saved_ski_place.text = skiPlaceExtendedName
            setOnClickListener {
                onItemClickListener(save)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}