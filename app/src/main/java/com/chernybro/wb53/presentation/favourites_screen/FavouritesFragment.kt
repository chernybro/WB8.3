package com.chernybro.wb53.presentation.favourites_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chernybro.wb53.databinding.FragmentFavouritesBinding
import com.chernybro.wb53.domain.data.Cat
import com.chernybro.wb53.domain.data.FavouriteCat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private val adapter = FavouritesListAdapter()

    private val vm: FavouritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter.attachClickHandler(object : CatClickHandler {
            override fun onFavClicked(item: FavouriteCat) {
                vm.deleteFavourite(item.id)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            container.adapter = adapter
        }
        vm.cats.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        vm.message.observe(viewLifecycleOwner) { message ->
            Log.d("TAG", "onViewCreated: $message")
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}