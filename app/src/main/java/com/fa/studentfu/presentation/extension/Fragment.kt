package com.fa.studentfu.presentation.extension

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.fa.studentfu.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(message : String, view : View){
    val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.white))
        .setActionTextColor(ContextCompat.getColor(requireContext(), R.color.green_fa))
        .setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    snackBar.setAction("Отмена") {
        snackBar.dismiss()
    }
    snackBar.show()
}

fun Fragment.activityNavController() = requireActivity().findNavController(R.id.nav_host_fragment)