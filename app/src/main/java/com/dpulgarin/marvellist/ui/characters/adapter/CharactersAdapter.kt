package com.dpulgarin.marvellist.ui.characters.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dpulgarin.marvellist.core.BaseViewHolder
import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.databinding.CharacterItemBinding
import com.dpulgarin.marvellist.ui.characters.CharactersListFragment

class CharactersAdapter(private val characterList: List<Character>,
                        private val itemClickListener: CharactersListFragment
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCharacterClickListener {
        fun onCharacterClick(character: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBindig = CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = CharacterViewHolder(itemBindig, parent.context)

        itemBindig.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onCharacterClick(characterList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is CharacterViewHolder -> holder.bind(characterList[position])
        }
    }

    override fun getItemCount(): Int = characterList.size

    private inner class CharacterViewHolder(val binding: CharacterItemBinding, val context: Context): BaseViewHolder<Character>(binding.root) {
        override fun bind(item: Character) {
            Glide.with(context)
                .load(item.thumbnail?.getUrl())
                .centerCrop()
                .into(binding.imgCharacter)
        }

    }
}