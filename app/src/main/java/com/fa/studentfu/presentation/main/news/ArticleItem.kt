package com.fa.studentfu.presentation.main.news

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fa.studentfu.BuildConfig
import com.fa.studentfu.R
import com.fa.studentfu.data.models.ArticleModel
import com.fa.studentfu.databinding.ArticleListElementBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class ArticleItem(private val article : ArticleModel, private val context: Context)
    : AbstractBindingItem<ArticleListElementBinding>() {
    override val type: Int
        get() = R.id.news_recycler

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ArticleListElementBinding {
        return ArticleListElementBinding.inflate(inflater, parent, false)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(binding: ArticleListElementBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.articleTime.text = "${article.dateTime.subSequence(0, 10)} ${article.dateTime.subSequence(11, 16)}"
        binding.articleHeader.text = article.header
        binding.articleText.text = article.description
        Glide.with(context)
            .load(article.imageUrl).into(binding.articleImage)
    }
}