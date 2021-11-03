package com.dpulgarin.marvellist.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dpulgarin.marvellist.R
import com.dpulgarin.marvellist.core.Resource
import com.dpulgarin.marvellist.data.remote.RemoteCharacterDatasource
import com.dpulgarin.marvellist.databinding.FragmentCharactersListBinding
import com.dpulgarin.marvellist.presentation.CharacterViewModel
import com.dpulgarin.marvellist.presentation.CharacterViewModelFactory
import com.dpulgarin.marvellist.repository.CharacterRepositoryImpl
import com.dpulgarin.marvellist.ui.characters.adapter.CharactersAdapter
import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.repository.WebService

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharactersListFragment : Fragment(R.layout.fragment_characters_list), CharactersAdapter.OnCharacterClickListener {

    private lateinit var binding: FragmentCharactersListBinding
    private val viewModel by viewModels<CharacterViewModel> {
        CharacterViewModelFactory(
            CharacterRepositoryImpl(
                RemoteCharacterDatasource(WebService.RetrofitClient.webService)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersListBinding.bind(view)

        viewModel.fetchMainScreenCharacters().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    /*if(result.data.data.results.isEmpty()) {
                        binding.emptyContainer.show()
                        return@Observer
                    } else {
                        binding.emptyContainer.hide()
                    } */
                    binding.rvCharacters.adapter = CharactersAdapter(result.data.data.results, this@CharactersListFragment)
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
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
    }

}