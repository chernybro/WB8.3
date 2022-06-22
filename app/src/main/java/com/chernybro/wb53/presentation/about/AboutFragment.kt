package com.chernybro.wb53.presentation.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chernybro.wb53.R
import com.chernybro.wb53.databinding.FragmentAboutBinding
import com.chernybro.wb53.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.projectLinkValue.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.github_project_link)))
            startActivity(browserIntent)
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).toggleInfoButton(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        (activity as MainActivity).toggleInfoButton(false)
    }
}