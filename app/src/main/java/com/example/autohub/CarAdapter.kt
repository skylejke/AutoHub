package com.example.autohub

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autohub.model.Car

class CarAdapter : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private var carList = emptyList<Car>()

    class CarViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.car_list_item, parent, false)
        return CarViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carInfo = carList[position]
        holder.itemView.findViewById<TextView>(R.id.car_title).text =
            carInfo.make + " " + carInfo.model + " " + carInfo.year
        holder.itemView.findViewById<TextView>(R.id.car_characteristics).text =
            carInfo.mileage + " " + carInfo.bodyType
        holder.itemView.findViewById<TextView>(R.id.car_price).text = carInfo.price
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