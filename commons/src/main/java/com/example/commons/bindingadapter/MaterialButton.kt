package com.example.commons.bindingadapter

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton

/**
 * Load a drawable as an icon into the MaterialButton
 * @param drawableId the drawable resource id
 */
@BindingAdapter("iconDrawable")
fun MaterialButton.setIconDrawable(drawableId: Int) {
    icon = ContextCompat.getDrawable(context, drawableId)
}