package com.example.retrofitassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.retrofitassignment.databinding.ItemUserBinding


class DataAdapter : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var data : List<Data>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.binding.apply {
            val data = data[position]
            tvEmail.text = data.email
            tvFirstName.text = data.first_name
            tvLastName.text = data.last_name
        }
    }
}