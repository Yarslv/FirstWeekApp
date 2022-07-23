package com.internship.firstweekapp.subarch

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.arch.data.repository.PreferenceStorage
import org.koin.android.ext.android.inject

abstract class BaseFragmentWithStorage<T : ViewDataBinding>(@LayoutRes private val resId: Int) :
    BaseFragment<T>(resId) {
    val storage: PreferenceStorage by inject()

    protected abstract val nextNav: NavDirections


    fun goNext() {
        findNavController().navigate(nextNav)
    }

    fun goBack() {
        findNavController().navigateUp()
    }
}