package com.villarica.villarica.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.villarica.villarica.databinding.ItemDonorBinding
import com.yodeck.models.donors_response.Data

class DonorsAdapter(val onItemClick: (item: Data) -> Unit): Adapter<RecyclerView.ViewHolder>() {

    var list = ArrayList<Data>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemDonorBinding.inflate(LayoutInflater.from(parent.context),parent,  false)
        return DonorsViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when(holder){
            is  DonorsViewHolder -> {
                holder.itemDonorsBinding.tvIndex.text = (position+1).toString()
                holder.itemDonorsBinding.tvName.text = item.name
                holder.itemDonorsBinding.tvCity.text = item.city
                holder.itemDonorsBinding.tvPhone.text = item.phone
                holder.itemDonorsBinding.tvBloodGroup.text = item.blood_group
                holder.itemDonorsBinding.root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }


    inner class DonorsViewHolder(itemView: View, val itemDonorsBinding: ItemDonorBinding): RecyclerView.ViewHolder(itemView){

    }
}