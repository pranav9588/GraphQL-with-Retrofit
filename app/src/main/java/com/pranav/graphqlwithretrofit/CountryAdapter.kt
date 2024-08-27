package com.pranav.graphqlwithretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pranav.graphqlwithretrofit.databinding.CountryListCardBinding

class CountryAdapter(private val list: List<DataModel.Data.Country?>?) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CountryListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryName: String) {
            binding.apply {
                countryTxt.text = countryName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryAdapter.ViewHolder {
        return ViewHolder(
            CountryListCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CountryAdapter.ViewHolder, position: Int) {
        list?.get(position)?.name?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

}
