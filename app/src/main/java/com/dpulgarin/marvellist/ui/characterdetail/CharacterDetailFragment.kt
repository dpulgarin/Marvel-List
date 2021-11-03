package com.dpulgarin.marvellist.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.dpulgarin.marvellist.R
import com.dpulgarin.marvellist.data.remote.RemoteCharacterDatasource
import com.dpulgarin.marvellist.databinding.FragmentCharacterDetailBinding
import com.dpulgarin.marvellist.presentation.CharacterViewModel
import com.dpulgarin.marvellist.presentation.CharacterViewModelFactory
import com.dpulgarin.marvellist.repository.CharacterRepositoryImpl
import com.dpulgarin.marvellist.repository.WebService
import com.dpulgarin.marvellist.data.models.Character

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
    }

}