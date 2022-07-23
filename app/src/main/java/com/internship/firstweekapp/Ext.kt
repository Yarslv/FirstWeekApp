package com.internship.firstweekapp

import android.graphics.BitmapFactory
import android.widget.RadioGroup
import androidx.annotation.NavigationRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.ui.setup_screens.confirm_class.UserClass
import com.internship.firstweekapp.ui.tabs.points_fragment.PointModel
import com.internship.firstweekapp.ui.tabs.points_fragment.PointsRecyclerViewAdapter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


fun Fragment.navigate(@NavigationRes dir: NavDirections) {
    findNavController().navigate(dir)
}

fun Fragment.navigateBack() {
    findNavController().navigateUp()
}

@BindingAdapter("app:buttonSelector")
fun MaterialButton.buttonOffOn(bool: Boolean) {
    isClickable = bool
    alpha = when (bool) {
        true -> {
            1f
        }
        false -> {
            0.25f
        }
    }
}

@BindingAdapter("app:buttonSelectorFromClass")
fun MaterialButton.buttonOffOn(userClass: UserClass) {
    val bool = userClass != UserClass.None
    isClickable = bool
    alpha = when (bool) {
        true -> {
            1f
        }
        false -> {
            0.25f
        }
    }
}

@BindingAdapter("app:addList")
fun addList(viewAdapter: RecyclerView, list: LiveData<ArrayList<PointModel>>) {
    list.observe(viewAdapter.findViewTreeLifecycleOwner()!!) {
        (viewAdapter.adapter as? PointsRecyclerViewAdapter)?.setContent(it)
    }

}

@BindingAdapter("android:setDate")
fun MaterialTextView.setDate(millis: Long) {
    val formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)
    val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
    text = context.getString(R.string.dateTimeFormat, formatter.format(date))
}


@BindingAdapter("app:setImage")
fun AppCompatImageView.setImage(user: UserClass) {
    when (user) {
        UserClass.None -> {}
        UserClass.Player -> {
            setImageBitmap(BitmapFactory.decodeResource(context.resources, R.raw.player))
        }
        UserClass.Hero -> {
            setImageBitmap(BitmapFactory.decodeResource(context.resources, R.raw.hero))
        }
        UserClass.Master -> {
            setImageBitmap(BitmapFactory.decodeResource(context.resources, R.raw.master))
        }
    }
}

@BindingAdapter("android:setCheckedButton")
fun RadioGroup.setCheckedButton(userClass: UserClass) {
    when (userClass) {
        UserClass.None -> {}
        UserClass.Player -> {
            check(R.id.playerButton)
        }
        UserClass.Hero -> {
            check(R.id.heroButton)
        }
        UserClass.Master -> {
            check(R.id.masterButton)
        }
    }
}

@InverseBindingAdapter(attribute = "android:setCheckedButton")
fun getRadioGroupValue(radioGroup: RadioGroup): UserClass {
    return when (radioGroup.checkedRadioButtonId) {
        R.id.playerButton -> {
            UserClass.Player
        }
        R.id.heroButton -> {
            UserClass.Hero
        }
        R.id.masterButton -> {
            UserClass.Master
        }
        else -> {
            UserClass.None
        }
    }
}

@BindingAdapter("android:setCheckedButtonAttrChanged")
fun setRadioGroupListeners(
    radioGroup: RadioGroup,
    attrChange: InverseBindingListener
) {
    radioGroup.setOnCheckedChangeListener { group, checkedId ->

        attrChange.onChange()
    }
}
