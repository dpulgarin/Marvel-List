package com.dpulgarin.marvellist.ui.characterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dpulgarin.marvellist.R
import com.dpulgarin.marvellist.application.AppConstants.ALPHA_DISABLED
import com.dpulgarin.marvellist.application.AppConstants.ALPHA_ENABLED
import com.dpulgarin.marvellist.core.Resource
import com.dpulgarin.marvellist.core.extensions.gone
import com.dpulgarin.marvellist.core.extensions.visible
import com.dpulgarin.marvellist.databinding.FragmentCharacterDetailBinding
import com.dpulgarin.marvellist.presentation.CharacterViewModel
import com.dpulgarin.marvellist.data.models.Character
import com.dpulgarin.marvellist.ui.characterdetail.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {
    private lateinit var binding: FragmentCharacterDetailBinding

    private val args by navArgs<CharacterDetailFragmentArgs>()
    private var character: Character? = null

    private val viewModel by activityViewModels<CharacterViewModel>()

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

        var overviewText = character?.description
        if (overviewText.isNullOrBlank()) {
            overviewText = getString(R.string.character_detail_empty_overview)
        }

        binding.txtDescription.text = overviewText

        character?.comics.let { comicsList ->
            if (comicsList?.items?.isNullOrEmpty() == true) {
                showEmptyView()
            } else {
                showRecycler()
                binding.rvCharacterList.adapter = comicsList?.getComicsNames()?.let { list ->
                    CharacterAdapter(list)
                }
            }
            binding.txtComics.text = getString(R.string.character_detail_comics_number, comicsList?.items?.size)
        }

        var seriesCount = 0
        if (character?.series?.items != null) {
            seriesCount = character?.series?.items?.size!!
        }
        binding.txtSeries.text = getString(R.string.character_detail_series_number, seriesCount)

        var eventsCount = 0
        if (character?.events?.items != null) {
            eventsCount = character?.events?.items?.size!!
        }
        binding.txtEvents.text = getString(R.string.character_detail_series_number, eventsCount)

        setListeners()
    }

    fun setListeners() {
        binding.linearComics.setOnClickListener {
            setAlphas(ALPHA_ENABLED, ALPHA_DISABLED, ALPHA_DISABLED)

            binding.txtTitleList.text = getString(R.string.character_detail_comics)

            if (character?.comics?.items.isNullOrEmpty()) {
                showEmptyView()
            } else {
                showRecycler()
                binding.rvCharacterList.adapter = character?.comics?.getComicsNames()?.let { list ->
                    CharacterAdapter(list)
                }
            }
        }

        binding.linearSeries.setOnClickListener {
            setAlphas(ALPHA_DISABLED, ALPHA_ENABLED, ALPHA_DISABLED)

            binding.txtTitleList.text = getString(R.string.character_detail_series)

            if (character?.series?.items.isNullOrEmpty()) {
                showEmptyView()
            } else {
                showRecycler()
                binding.rvCharacterList.adapter = character?.series?.getSeriesNames()?.let { list ->
                    CharacterAdapter(list)
                }
            }
        }

        binding.linearEvents.setOnClickListener {
            setAlphas(ALPHA_DISABLED, ALPHA_DISABLED, ALPHA_ENABLED)

            binding.txtTitleList.text = getString(R.string.character_detail_events)

            if (character?.events?.items.isNullOrEmpty()) {
                showEmptyView()
            } else {
                showRecycler()
                binding.rvCharacterList.adapter = character?.events?.getEventsNames()?.let { list ->
                    CharacterAdapter(list)
                }
            }
        }
    }

    fun setAlphas(alphaComics: Float, alphaSeries: Float, alphaEvents: Float) {
        binding.linearComics.alpha = alphaComics
        binding.linearSeries.alpha = alphaSeries
        binding.linearEvents.alpha = alphaEvents
    }

    fun showRecycler() {
        binding.txtEmptyView.gone()
        binding.rvCharacterList.visible()
    }

    fun showEmptyView() {
        binding.txtEmptyView.visible()
        binding.rvCharacterList.gone()
    }

}