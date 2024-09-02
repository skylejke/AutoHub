package com.example.autohub.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autohub.R
import com.example.autohub.databinding.FragmentCarPhotoItemBinding

class CarPhotosAdapter : RecyclerView.Adapter<CarPhotosAdapter.PhotoViewHolder>() {

    var carPhotoList = emptyList<String>()
        set(value) {
            val callback = object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return field.size
                }

                override fun getNewListSize(): Int {
                    return value.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return field[oldItemPosition] == value[newItemPosition]
                }

                override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
                ): Boolean {
                    return field[oldItemPosition] == value[newItemPosition]
                }
            }
            val difResult = DiffUtil.calculateDiff(callback)
            difResult.dispatchUpdatesTo(this)
            field = value
        }

    class PhotoViewHolder(private val binding: FragmentCarPhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photoUrl: String) {
            binding.carPhoto.load(photoUrl) {
                error(R.drawable.error)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            FragmentCarPhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return carPhotoList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(carPhotoList[position])
    }

}

