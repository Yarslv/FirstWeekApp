package com.internship.firstweekapp

import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.canhub.cropper.CropImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.ui.photo_editor.ButtonState
import com.internship.firstweekapp.ui.photo_editor.EditorAction
import com.internship.firstweekapp.ui.photo_editor.ShadeFilter

fun Fragment.makeToast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}

@BindingAdapter("setLabel")
fun MaterialButton.setLabel(state: ButtonState) {
    text = when (state) {
        ButtonState.Apply -> context.getString(R.string.apply_button_text)
        ButtonState.Save -> context.getString(R.string.save_button_text)
        ButtonState.Crop -> context.getString(R.string.crop_button_text)
    }
}

@BindingAdapter("setText")
fun MaterialTextView.setText(shadeFilter: ShadeFilter) {
    text = when (shadeFilter) {
        ShadeFilter.Normal -> {
            context.getString(R.string.normal_filter_name)
        }
        ShadeFilter.Invert -> {
            context.getString(R.string.invert_filter_name)
        }
        ShadeFilter.RGBtoBRG -> {
            context.getString(R.string.rgb_to_brg_filter_name)
        }
        ShadeFilter.Sepia -> {
            context.getString(R.string.sepia_filter_name)
        }
        ShadeFilter.BlackAndWhite -> {
            context.getString(R.string.black_and_white_filter_name)
        }
        ShadeFilter.Polaroid -> {
            context.getString(R.string.polaroid_filter_name)
        }
        ShadeFilter.WhiteToAlpha -> {
            context.getString(R.string.white_to_alpha)
        }
        ShadeFilter.Old -> {
            context.getString(R.string.old_filter_name)
        }
    }
}

@BindingAdapter("setFilter")
fun AppCompatImageView.setFilter(shadeFilter: ShadeFilter) {
    colorFilter = shadeFilter.colorFilter
}

@BindingAdapter("setBitmap")
fun AppCompatImageView.setBitmap(bitmap: Bitmap) {
    setImageBitmap(bitmap)
}

@BindingAdapter("setBitmap")
fun CropImageView.setBitmap(bitmap: Bitmap) {
    setImageBitmap(bitmap)
}


@BindingAdapter("setContrast")
fun AppCompatImageView.setContrast(value: Float) {

    val colorMatrix2 = ColorMatrix(
        floatArrayOf(
            value, 0f, 0f, 0f, 0f,
            0f, value, 0f, 0f, 0f,
            0f, 0f, value, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )
    colorFilter = ColorMatrixColorFilter(colorMatrix2)
}

@BindingAdapter("setBrightness")
fun ImageView.setBrightness(value: Float) {
    val colorMatrix2 = ColorMatrix(
        floatArrayOf(
            1f, 0f, 0f, 0f, value,
            0f, 1f, 0f, 0f, value,
            0f, 0f, 1f, 0f, value,
            0f, 0f, 0f, 1f, 0f
        )
    )
    colorFilter = ColorMatrixColorFilter(colorMatrix2)
}

@BindingAdapter("setSliderPosition")
fun Slider.setPosition(pos: Float) {
    value = pos
}

@InverseBindingAdapter(attribute = "setSliderPosition")
fun getPosition(slider: Slider): Float {
    return slider.value
}


@BindingAdapter("setSliderPositionAttrChanged")
fun setSliderListeners(
    slider: Slider,
    attrChange: InverseBindingListener
) {
    slider.addOnChangeListener { _, _, _ ->
        attrChange.onChange()
    }
}


@BindingAdapter("setPosition")
fun TabLayout.setPosition(pos: EditorAction) {
    getTabAt(pos.position)!!.select()
}

@InverseBindingAdapter(attribute = "setPosition")
fun getPosition(tabs: TabLayout): EditorAction {
    return when (tabs.selectedTabPosition) {
        0 -> {
            EditorAction.Crop
        }
        1 -> {
            EditorAction.Brightness
        }
        2 -> {
            EditorAction.Contrast
        }
        else -> {
            EditorAction.Shade
        }
    }
}


@BindingAdapter("setPositionAttrChanged")
fun setTabLayoutListeners(
    tabs: TabLayout,
    attrChange: InverseBindingListener
) {
    tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            attrChange.onChange()
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    })
}