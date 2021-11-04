package com.dpulgarin.marvellist.ui.characterdetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dpulgarin.marvellist.core.BaseViewHolder
import com.dpulgarin.marvellist.databinding.CharacterListsItemBinding

class CharacterAdapter(private val characterList: List<String>
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBindig =
            CharacterListsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = CharacterViewHolder(itemBindig, parent.context)

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is CharacterViewHolder -> holder.bind(characterList[position])
        }
    }

    override fun getItemCount(): Int = characterList.size

    private inner class CharacterViewHolder(
        val binding: CharacterListsItemBinding,
        val context: Context
    ) : BaseViewHolder<String>(binding.root) {
        override fun bind(item: String) {
            binding.txtCharacterItem.text = item
        }

    }
}