package com.example.commons.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.analytics.CommonEvents
import com.example.analytics.CommonParams
import com.example.analytics.IAnalyticsLog
import org.koin.android.ext.android.inject

abstract class BaseFragment(
    @LayoutRes private val layoutId: Int,
    private val screenLogName: String,
) : Fragment() {

    val analyticsLog: IAnalyticsLog by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(layoutId, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        analyticsLog.logEvent(
            CommonEvents.SHOW_FRAGMENT,
            bundleOf(CommonParams.FRAGMENT_NAME to screenLogName)
        )
    }

}

abstract class BaseBindingFragment<VDB : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    screenLogName: String,
) : BaseFragment(
    layoutId,
    screenLogName
) {

    lateinit var binding: VDB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = (DataBindingUtil.inflate(inflater, layoutId, container, false) as? VDB)?.apply {
        lifecycleOwner = viewLifecycleOwner
        binding = this
    }?.root

}