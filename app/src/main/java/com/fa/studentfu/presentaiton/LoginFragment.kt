package com.fa.studentfu.presentaiton

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fa.studentfu.R
import com.fa.studentfu.databinding.RegistrationFragmentBinding

class LoginFragment : Fragment() {
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
        binding.sectionRegistration.visibility = View.GONE
        binding.registrationSwitch.text = resources.getString(R.string.goto_register)
        binding.registrationSwitch.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        binding.register.text = resources.getString(R.string.login)
        binding.registrationSwitch.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginRegister())
        }
    }
}