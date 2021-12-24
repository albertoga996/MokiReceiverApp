package com.example.mokireceiverapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mokireceiverapp.MainActivity
import com.example.mokireceiverapp.R
import com.example.mokireceiverapp.databinding.FragmentReceiverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceiverFragment : Fragment(R.layout.fragment_receiver) {

    private lateinit var binding: FragmentReceiverBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReceiverBinding.bind(view)

        binding.factText.text = (activity as MainActivity).getFact()
    }
}