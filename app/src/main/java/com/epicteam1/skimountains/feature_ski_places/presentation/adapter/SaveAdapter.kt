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
import com.epicteam1.skimountains.feature_ski_places.domain.model.SkiPlace
import kotlinx.android.synthetic.main.item_save_ski_place.view.*

class SaveAdapter : RecyclerView.Adapter<SaveAdapter.ViewHolder>() {
    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

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
            LayoutInflater.from(parent.context).inflate(R.layout.item_save_ski_place, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val save = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(context).load(save.mainPic).into(image_view_item_saved_ski_place)
            name_saved_ski_place.text =
                save.nameRus + " " + save.regionRus
            setOnClickListener {
                onItemClickListener?.let { it(save) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((SkiPlace) -> Unit)? = null
    fun setOnItemClickListener(listener: (SkiPlace) -> Unit) {
        onItemClickListener = listener
    }
}