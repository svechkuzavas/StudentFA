package com.fa.studentfu.presentation.main.news

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.fa.studentfu.core.ui.BaseFragment
import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.databinding.NewsFragmentBinding
import com.fa.studentfu.domain.models.ScheduleModel
import com.fa.studentfu.presentation.main.timetable.TimeTableItem
import com.fa.studentfu.presentation.main.timetable.TimetableViewModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<NewsFragmentBinding>(NewsFragmentBinding::inflate) {

    private val viewModel by viewModel<NewsViewModel>()

    private var newsAdapter  = ItemAdapter<ArticleItem>()

    override fun initializeViews() {
        super.initializeViews()
        setupRecycler()
        viewModel.fetchNews()
    }

    override fun setupListeners() {
        super.setupListeners()
    }

    override fun observe() {
        super.observe()
        viewModel.state.observe(viewLifecycleOwner){
            when (it) {
                is NewsViewModel.UiState.Loading -> {
                    binding.newsProgress?.visibility = View.VISIBLE
                }
                is NewsViewModel.UiState.ResultAvailable -> {
                    refreshRecycler(it.articles)
                }
            }
        }
    }

    private fun setupRecycler(){
        binding.newsRecycler?.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )
        val timetableFastAdapter = FastAdapter.with(newsAdapter)
        binding.newsRecycler?.adapter = timetableFastAdapter
    }

    private fun refreshRecycler(articleList : List<ArticleModel>){
        FastAdapterDiffUtil[newsAdapter] = articleList.map(::ArticleItem)
    }
}