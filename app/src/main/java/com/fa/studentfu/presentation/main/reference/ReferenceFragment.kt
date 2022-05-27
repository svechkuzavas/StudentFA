package com.fa.studentfu.presentation.main.reference

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.data.models.ReferenceModel
import com.fa.studentfu.databinding.ReferencesFragmentBinding
import com.fa.studentfu.presentation.main.news.ArticleItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReferenceFragment : BaseFragment<ReferencesFragmentBinding>(ReferencesFragmentBinding::inflate){

    val viewModel by viewModel<ReferenceViewModel>()

    private var referenceAdapter  = ItemAdapter<ReferenceItem>()

    override fun initializeViews() {
        super.initializeViews()
        viewModel.fetchReferences()
        setupRecycler()
    }

    override fun setupListeners() {
        super.setupListeners()
        binding.createReference.setOnClickListener {
            ReferenceAddBottomSheetDialog().show(childFragmentManager, "")
        }
    }

    override fun observe() {
        super.observe()
        viewModel.state.observe(viewLifecycleOwner){
            when (it) {
                is ReferenceViewModel.UiState.Loading -> {
                    binding.referenceProgress?.visibility = View.VISIBLE
                }
                is ReferenceViewModel.UiState.ResultAvailable -> {
                    binding.referenceProgress?.visibility = View.GONE
                    refreshRecycler(it.references)
                }
            }
        }
    }

    private fun setupRecycler(){
        binding.referenceRecycler?.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        val referenceFastAdapter = FastAdapter.with(referenceAdapter)
        binding.referenceRecycler?.adapter = referenceFastAdapter
    }

    private fun refreshRecycler(referenceList : List<ReferenceModel>){
        val items : ArrayList<ReferenceItem> = ArrayList()
        referenceList.forEach{
            context?.let { it1 -> ReferenceItem(it, it1) }?.let { it2 -> items.add(it2) }
        }
        FastAdapterDiffUtil[referenceAdapter] = items
    }
}