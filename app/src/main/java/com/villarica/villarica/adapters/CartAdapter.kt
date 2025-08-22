package com.villarica.villarica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.villarica.villarica.databinding.ItemCartBinding
import com.villarica.villarica.databinding.ItemProductBinding

class CartAdapter: Adapter<RecyclerView.ViewHolder>() {

    var list = ArrayList<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context),parent,  false)
        return CartViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return 9
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


    inner class CartViewHolder(itemView: View, val itemCartBinding: ItemCartBinding): RecyclerView.ViewHolder(itemView){

    }
}