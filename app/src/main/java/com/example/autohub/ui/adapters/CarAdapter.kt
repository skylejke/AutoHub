package com.example.autohub.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autohub.R
import com.example.autohub.databinding.CarListItemBinding
import com.example.autohub.domain.model.CarDomain


class CarAdapter : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    var carList = emptyList<CarDomain>()
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

    lateinit var carClickbale: CarClickbale

    interface CarClickbale {
        fun onCarClick(carDomain: CarDomain)
    }

    class CarViewHolder(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        private val binding = CarListItemBinding.bind(view)


        fun bind(car: CarDomain) = with(binding) {
            carTitle.text = context.getString(R.string.car_info, car.make, car.model, car.year)
            carPrice.text = car.price
            Glide.with(root)
                .load(car.primaryPhotoUrl)
                .into(carPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.car_list_item, parent, false)
        return CarViewHolder(view, parent.context)
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