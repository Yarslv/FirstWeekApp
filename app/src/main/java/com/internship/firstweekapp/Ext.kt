package com.internship.firstweekapp

import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.ui.add_location.Error
import com.internship.firstweekapp.ui.tabs.cities_list.recycler.CitiesListAdapter
import com.internship.firstweekapp.ui.tabs.cities_list.recycler.CityModel
import com.internship.firstweekapp.ui.tabs.setting.Languages
import com.internship.firstweekapp.ui.tabs_screen.TabsNames

@BindingAdapter("setImage")
fun AppCompatImageView.setImage(url: String?) {
    Glide.with(context).load("https://" + url?.replace("64x64", "128x128")).into(this)
}

@BindingAdapter("setSrc")
fun AppCompatImageView.setSrc(bool: Boolean) {
    if (bool) {
        setImageResource(R.drawable.ic_baseline_home_24)
    } else {
        setImageResource(R.drawable.ic_baseline_add_home_24)
    }
}


    @BindingAdapter("setError")
    fun TextInputLayout.setError(err: Error) {
        error = when (err) {
            Error.Empty -> context.getString(R.string.no_empty_msg)
            Error.UnknownLocation -> context.getString(R.string.unknown_loc_msg)
            Error.None -> ""
        }
    }

    @BindingAdapter("setItems")
    fun RecyclerView.setItems(items: List<CityModel>) {
        (adapter as CitiesListAdapter).resubmitList(items)
    }

    @BindingAdapter("setLanguage")
    fun MaterialTextView.setLanguage(lang: Languages) {
        text = when (lang) {
            Languages.UK -> {
                context.getString(R.string.ukrainian)
            }
            Languages.EN -> {
                context.getString(R.string.english)
            }
        }
    }

    @BindingAdapter("setLanguage")
    fun RadioGroup.setLanguage(lang: Languages) {
        check(
            when (lang) {
                Languages.UK -> {
                    R.id.UK
                }
                Languages.EN -> {
                    R.id.EN
                }
            }
        )
    }

    @InverseBindingAdapter(attribute = "setLanguage")
    fun RadioGroup.getLanguage(): Languages {
        return when (checkedRadioButtonId) {
            R.id.UK -> {
                Languages.UK

            }
            else -> {
                Languages.EN
            }
        }
    }

    @BindingAdapter("setLanguageAttrChanged")
    fun setRadioGroupListeners(
        group: RadioGroup,
        attrChange: InverseBindingListener
    ) {
        group.setOnCheckedChangeListener { _, _ ->
            attrChange.onChange()
        }
    }

    @BindingAdapter("setCurrentTab")
    fun TabLayout.setCurrentTab(current: TabsNames) {
        val tab = getTabAt(
            when (current) {
                TabsNames.List -> {
                    0
                }
                TabsNames.Home -> {
                    1
                }
                TabsNames.Setting -> {
                    2
                }
            }
        )
        tab?.select()
    }

    @InverseBindingAdapter(attribute = "setCurrentTab")
    fun TabLayout.getCurrentTab(): TabsNames {
        return when (selectedTabPosition) {
            0 -> TabsNames.List
            1 -> TabsNames.Home
            else -> TabsNames.Setting
        }
    }

    @BindingAdapter("setCurrentTabAttrChanged")
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
