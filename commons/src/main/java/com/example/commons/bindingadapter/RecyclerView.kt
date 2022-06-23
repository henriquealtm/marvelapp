package com.example.commons.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.extension.handleOpt

@BindingAdapter("smoothScrollToBottom")
fun RecyclerView.smoothScrollToBottom(
    mustScroll: Boolean?
) {
    if (mustScroll.handleOpt().not() || adapter == null) return

    val adapter = this@smoothScrollToBottom.adapter ?: return

    if (mustScroll.handleOpt()) {
        smoothScrollToPosition(adapter.itemCount - 1)
    }
}

@BindingAdapter("onScrollToBottom")
fun RecyclerView.onScrollToBottom(
    block: (() -> Unit)?
) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(1) &&
                newState == RecyclerView.SCROLL_STATE_IDLE
            ) {
                block?.invoke()
            }
        }
    })
}