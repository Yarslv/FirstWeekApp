package com.internship.firstweekapp.ui.card_list

import android.graphics.drawable.Drawable
import android.util.Log
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
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.ext.bindGifView
import com.internship.firstweekapp.ui.card_list.adapter.CardAdapter
import kotlinx.coroutines.coroutineScope

fun Fragment.navigate(dir: NavDirections) {
    findNavController().navigate(dir)

}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}

@BindingAdapter(value =  ["setList", "setList2"], requireAll = true)
fun RecyclerView.setList(arr: ArrayList<CardItem>, arr2: List<CardItem>) {
    arr.addAll(arr2)
    Log.d("recwWW", arr.toArray().toString())
    (adapter as CardAdapter).setContent(arr)
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


@BindingAdapter(value = ["setCardTypee", "setCardImagee", "setCardValuee"], requireAll = true)
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
            when (SwitchValue.valueOf(value)) {
                SwitchValue.on -> {
                    setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_baseline_toggle_on_24
                        )
                    )
                }
                SwitchValue.off -> {
                    setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_baseline_toggle_off_24
                        )
                    )
                }
            }
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
                    when (SwitchValue.valueOf(value)) {
                        SwitchValue.on -> setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_baseline_volume_up_24
                            )
                        )
                        SwitchValue.off -> setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_baseline_volume_off_24
                            )
                        )
                    }
                }

                SensorType.Light -> {}
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