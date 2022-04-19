package com.fa.studentfu.core.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseFlowFragment(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected lateinit var childFM: FragmentManager

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFM = childFragmentManager
        setupNavigation()
    }

    protected open fun setupNavigation() {}
}