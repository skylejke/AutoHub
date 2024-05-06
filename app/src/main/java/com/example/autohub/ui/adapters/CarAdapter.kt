package com.example.autohub.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.autohub.R
import com.example.autohub.databinding.CarListItemBinding
import com.example.autohub.domain.model.CarVo


class CarAdapter(private val carClickbale: CarClickbale) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    var carList = emptyList<CarVo>()
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

    interface CarClickbale {
        fun onCarClick(carVo: CarVo)
    }

    class CarViewHolder(
        private val binding: CarListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: CarVo) = with(binding) {
            carTitle.text = root.context.getString(R.string.car_info, car.make, car.model, car.year)
            carPrice.text = car.price
            carPhoto.load(car.primaryPhotoUrl)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList[position])
        holder.itemView.setOnClickListener {
            carClickbale.onCarClick(carList[position])
        }
    }

    override fun getItemCount(): Int {
        return carList.size
    }
}