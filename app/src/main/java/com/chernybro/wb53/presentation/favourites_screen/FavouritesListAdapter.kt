package com.chernybro.wb53.presentation.favourites_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chernybro.wb53.databinding.ItemFavouriteCatBinding
import com.chernybro.wb53.domain.data.FavouriteCat

interface CatClickHandler {
    fun onFavClicked(item: FavouriteCat)
}

class FavouritesListAdapter : RecyclerView.Adapter<FavouritesListAdapter.HeroListViewHolder>() {
    private val data: MutableList<FavouriteCat> = ArrayList()
    private var favClickHandler: CatClickHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        val binding = ItemFavouriteCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeroListViewHolder(binding, heroClickHandler = favClickHandler)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        holder.bind(item = data[position])
    }

    fun setData(items: List<FavouriteCat>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun attachClickHandler(favClickHandler: CatClickHandler) {
        this.favClickHandler = favClickHandler
    }

    class HeroListViewHolder(
        private val itemCatBinding: ItemFavouriteCatBinding,
        private val heroClickHandler: CatClickHandler?,
    ) : RecyclerView.ViewHolder(itemCatBinding.root) {

        fun bind(item: FavouriteCat) {
            itemCatBinding.apply {
                ivCat.setImageURI(item.image.url)
                buttonLike.setOnClickListener{ heroClickHandler?.onFavClicked(item) }
            }
        }
    }
}