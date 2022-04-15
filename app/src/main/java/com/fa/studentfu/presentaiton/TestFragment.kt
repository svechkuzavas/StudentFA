package com.fa.studentfu.presentaiton

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fa.studentfu.databinding.TestFragmentBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowWith
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestFragment : Fragment() {
    private var _binding : TestFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TestFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.observeGroups().collect{
                Log.d( "onViewCreated: ", it.toString())
            }
        }

        binding.input.setOnClickListener{
            viewModel.getGroups("ПИ20")
        }
    }
}