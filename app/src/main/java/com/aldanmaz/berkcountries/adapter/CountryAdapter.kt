package com.aldanmaz.berkcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aldanmaz.berkcountries.R
import com.aldanmaz.berkcountries.databinding.ItemCountryBinding
import com.aldanmaz.berkcountries.model.Country
import com.aldanmaz.berkcountries.util.downloadFromUrl
import com.aldanmaz.berkcountries.util.placeHolderProgressBar
import com.aldanmaz.berkcountries.view.CountryListFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(),CountryClickListener{

    class CountryViewHolder(var view: ItemCountryBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
      //  val view = inflater.inflate(R.layout.item_country,parent,false)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.view.country = countryList[position]
        holder.view.listener = this

        /*
        holder.view.name.text = countryList[position].countryName

        holder.view.region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

     holder.view.imageView.downloadFromUrl(countryList[position].imageUrl, placeHolderProgressBar(holder.view.context))

     */
    }

    fun updateCountryList(newCountryList:List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged() //adapter i yenilemek icin kullaniyoruz.
    }

    override fun onCountryClick(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}