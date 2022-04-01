package xyz.scoca.countriesapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.scoca.countriesapp.databinding.CountryItemRowBinding
import xyz.scoca.countriesapp.model.CountryData
import xyz.scoca.countriesapp.util.OnItemClickListener
import xyz.scoca.countriesapp.util.extensions.downloadFromUrl
import xyz.scoca.countriesapp.util.extensions.placeHolderProgressBar

class CountryAdapter(
    private val context : Context,
    private val countryList : ArrayList<CountryData>,
    private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){

    inner class CountryViewHolder(private val itemBinding : CountryItemRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(){
            val itemPosition = countryList[adapterPosition]
            itemBinding.tvCountryName.text = itemPosition.countryName.toString()
            itemBinding.tvCountryCapital.text = itemPosition.countryCapital.toString()

            itemBinding.ivCountryFlag.downloadFromUrl(itemPosition.countryFlagUrl.toString(),
                placeHolderProgressBar(context))

            itemBinding.ivCountryFlag.setOnClickListener {
                onItemClickListener.onClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemBinding =
            CountryItemRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return CountryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = countryList.size

    fun updateCountryList(newList : List<CountryData>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }
}