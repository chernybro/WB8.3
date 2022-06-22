package com.chernybro.wb53.presentation.votes_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chernybro.wb53.R
import com.chernybro.wb53.databinding.FragmentVotesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VotesFragment : Fragment() {

    private var _binding: FragmentVotesBinding? = null
    private val binding get() = _binding!!

    private val vm: VotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            favouritesBtn.setOnClickListener {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
            buttonLike.setOnClickListener { vm.cat.value?.let { it1 -> vm.toFavourite(it1.id) } }
            buttonDislike.setOnClickListener { vm.getCat() }
        }
        vm.cat.observe(viewLifecycleOwner) { cat ->
            binding.ivCat.setImageURI(cat.url)
        }
        vm.message.observe(viewLifecycleOwner) { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}