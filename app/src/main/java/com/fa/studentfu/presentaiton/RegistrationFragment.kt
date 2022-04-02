package com.fa.studentfu.presentaiton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fa.studentfu.databinding.RegistrationFragmentBinding
import android.view.View as View

class RegistrationFragment: Fragment() {

    private var _binding : RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registrationSwitch.setOnClickListener{
            findNavController().navigate(RegistrationFragmentDirections.actionRegisterLogin())
        }
    }
}