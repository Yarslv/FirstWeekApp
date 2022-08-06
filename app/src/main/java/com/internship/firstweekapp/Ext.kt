package com.internship.firstweekapp

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.ui.card_list.item_model.CardSensorItem
import com.internship.firstweekapp.util.enums.Error
import com.internship.firstweekapp.ui.card_list.recycler.CardAdapter
import com.internship.firstweekapp.util.enums.SensorSubtype
import com.internship.firstweekapp.util.enums.SensorType
import com.internship.firstweekapp.util.enums.SwitchValue

fun Fragment.navigate(dir: NavDirections) {
    findNavController().navigate(dir)

}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}

@BindingAdapter("android:setErrorMessage")
fun TextInputLayout.setErrorMessage(errorType: Error) {

    error = when (errorType) {
        Error.EmptyTextFiled -> {
            "Write room name!"
        }
        Error.NoError -> {
            ""
        }
        Error.EmptyTypeDropDownField -> {
            "Select type!"
        }
        Error.EmptySubtypeDropDownField -> {
            "Select subtype!"
        }
    }

}


@BindingAdapter(value = ["setRepositoryList", "setLocalList"], requireAll = false)
fun RecyclerView.setList(repositoryList: List<CardSensorItem>, localList: List<CardSensorItem>) {
    (adapter as CardAdapter).setContent(repositoryList + localList)
}

@BindingAdapter("setCardIcon")
fun AppCompatImageView.setCardIcon(type: SensorType) {
    setImageDrawable(
        when (type) {
            SensorType.Sensor -> {
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_sensors_24)
            }
            SensorType.Camera -> {
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_camera_indoor_24)
            }
            SensorType.Sound -> {
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_music_note_24)
            }
            SensorType.Light -> {
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_light_mode_24)
            }
        }
    )
}


@BindingAdapter(value = ["setCardType", "setCardImage", "setCardValue"], requireAll = true)
fun MaterialTextView.setCardText(type: SensorType, subtype: SensorSubtype, value: String) {

    when (subtype) {
        SensorSubtype.switch -> {
            text = ""
        }
        SensorSubtype.onetime -> {
            text = ""

        }
        SensorSubtype.level -> {
            when (type) {
                SensorType.Sensor -> {}
                SensorType.Camera -> {}
                SensorType.Sound -> {
                    text = value
                }
                SensorType.Light -> {
                    text = value
                }
            }
        }
    }
}


@BindingAdapter(value = ["setCardType", "setCardImage", "setCardValue"], requireAll = true)
fun AppCompatImageView.setCardImage(type: SensorType, subtype: SensorSubtype, value: String) {

    when (subtype) {
        SensorSubtype.switch -> {
            visibility = View.VISIBLE
            setDrawableBySwitch(
                this,
                SwitchValue.valueOf(value),
                R.drawable.ic_baseline_toggle_on_24,
                R.drawable.ic_baseline_toggle_off_24
            )

        }
        SensorSubtype.onetime -> {
            visibility = View.VISIBLE
            when (type) {
                SensorType.Sensor -> {}
                SensorType.Camera -> {
                    Glide.with(context)
                        .asDrawable()
                        .load(value)
                        .placeholder(R.drawable.ic_baseline_no_photography_24)
                        .error(R.drawable.ic_baseline_error_24)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                return false
                            }
                        }).into(this)
                }
                SensorType.Sound -> {
                    setDrawableBySwitch(
                        this,
                        SwitchValue.valueOf(value),
                        R.drawable.ic_baseline_volume_up_24,
                        R.drawable.ic_baseline_volume_off_24
                    )
                }
                SensorType.Light -> {
                    setDrawableBySwitch(
                        this,
                        SwitchValue.valueOf(value),
                        R.drawable.ic_baseline_wb_sunny_24,
                        R.drawable.ic_baseline_nightlight_24
                    )

                }
            }
        }
        SensorSubtype.level -> {
            when (type) {
                SensorType.Sensor -> {}
                SensorType.Camera -> {}
                SensorType.Sound -> {
                    visibility = View.INVISIBLE
                }
                SensorType.Light -> {
                    visibility = View.INVISIBLE
                }
            }
        }
    }
}

fun setDrawableBySwitch(
    appCompatImageView: AppCompatImageView,
    switch: SwitchValue,
    onImg: Int,
    offImg: Int
) {
    appCompatImageView.setImageDrawable(
        when (switch) {
            SwitchValue.on -> {

                ContextCompat.getDrawable(
                    appCompatImageView.context,
                    onImg
                )
            }
            SwitchValue.off -> {
                ContextCompat.getDrawable(
                    appCompatImageView.context,
                    offImg
                )
            }
        }
    )
}
