package com.dpulgarin.marvellist.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.dpulgarin.marvellist.R
import com.dpulgarin.marvellist.databinding.FragmentCharactersListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [CharactersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharactersListFragment : Fragment(R.layout.fragment_characters_list) {

    private lateinit var binding: FragmentCharactersListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharactersListBinding.bind(view)


    }


}