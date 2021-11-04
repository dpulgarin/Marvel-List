package com.dpulgarin.marvellist.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.dpulgarin.marvellist.R
import com.dpulgarin.marvellist.core.Resource
import com.dpulgarin.marvellist.core.extensions.gone
import com.dpulgarin.marvellist.core.extensions.visible
import com.dpulgarin.marvellist.databinding.FragmentCharactersListBinding
import com.dpulgarin.marvellist.presentation.CharacterViewModel
import com.dpulgarin.marvellist.ui.characters.adapter.CharactersAdapter
import com.dpulgarin.marvellist.data.models.Character
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CharactersListFragment : Fragment(R.layout.fragment_characters_list), CharactersAdapter.OnCharacterClickListener {

    private lateinit var binding: FragmentCharactersListBinding
    private val viewModel by activityViewModels<CharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersListBinding.bind(view)

        getCharactersList()
    }

    fun getCharactersList() {
        viewModel.fetchMainScreenCharacters().observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visible()
                }

                is Resource.Success -> {
                    binding.progressBar.gone()
                    binding.rvCharacters.adapter = CharactersAdapter(result.data, this@CharactersListFragment)
                }

                is Resource.Failure -> {
                    binding.progressBar.gone()
                    Toast.makeText(
                        requireContext(),
                        "Ocurrio un error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    override fun onCharacterClick(character: Character) {
        Log.d("Character", "onCharacterClick: $character")

        val action = CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailFragment(character, character.id)
        findNavController().navigate(action)
    }

}