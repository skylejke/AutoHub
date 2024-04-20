package com.example.autohub

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.autohub.databinding.CarListItemBinding
import com.example.autohub.model.Car

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private var carList = emptyList<Car>()

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CarListItemBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(car: Car) = with(binding) {
            carTitle.text = car.make + " " + car.model + " " + car.year
            carCharacteristics.text = car.mileage + ", " + car.bodyType + ", " + car.condition
            carPrice.text = car.price
            Glide.with(root)
                .load(car.primaryPhotoUrl)
                .into(carPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.car_list_item, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carList[position])
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Car>) {
        carList = list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        carList = emptyList()
        notifyDataSetChanged()
    }
}