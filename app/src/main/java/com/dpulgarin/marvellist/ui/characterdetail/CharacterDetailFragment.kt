package com.dpulgarin.marvellist.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dpulgarin.marvellist.R
import com.dpulgarin.marvellist.core.Resource
import com.dpulgarin.marvellist.core.extensions.gone
import com.dpulgarin.marvellist.core.extensions.visible
import com.dpulgarin.marvellist.data.remote.RemoteCharacterDatasource
import com.dpulgarin.marvellist.databinding.FragmentCharacterDetailBinding
import com.dpulgarin.marvellist.presentation.CharacterViewModel
import com.dpulgarin.marvellist.presentation.CharacterViewModelFactory
import com.dpulgarin.marvellist.repository.CharacterRepositoryImpl
import com.dpulgarin.marvellist.repository.WebService
import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.ui.characters.adapter.CharactersAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var binding: FragmentCharacterDetailBinding

    private val args by navArgs<CharacterDetailFragmentArgs>()
    private var character: Character? = null

    private val viewModel by viewModels<CharacterViewModel> {
        CharacterViewModelFactory(
            CharacterRepositoryImpl(
                RemoteCharacterDatasource(WebService.RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDetailBinding.bind(view)

        character = args.character
        val characterId = args.characterId

        getCharacterById(characterId)
    }

    fun getCharacterById(characterId: Int) {
        viewModel.fetchDetailScreenCharacterById(characterId).observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visible()
                }

                is Resource.Success -> {
                    binding.progressBar.gone()
                    character = result.data.data.results[0]
                    initCharacter()
                }

                is Resource.Failure -> {
                    binding.progressBar.gone()
                    initCharacter()
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    fun initCharacter() {
        Glide.with(requireContext())
            .load(character?.thumbnail?.getUrl())
            .centerCrop()
            .into(binding.imgCharacter)

        binding.txtName.text = character?.name
        binding.txtDescription.text = character?.description
    }

}