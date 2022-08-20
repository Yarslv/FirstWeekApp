package com.internship.firstweekapp

import android.text.Editable
import android.text.TextWatcher

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.media_player.MediaPlayerProvider
import com.internship.firstweekapp.ui.result_list.RecordListItemModel
import com.internship.firstweekapp.ui.result_list.ResponseError
import com.internship.firstweekapp.ui.result_list.recycler.RecordingsAdapter
import com.internship.firstweekapp.ui.search.OptionalSearchParamModel
import com.internship.firstweekapp.ui.search.OptionalSearchParams
import com.internship.firstweekapp.ui.search.SearchType
import com.internship.firstweekapp.ui.search.recycler.OptionalFieldAdapter
import com.internship.firstweekapp.ui.search.recycler.RecyclerListener
import java.lang.StringBuilder


fun Fragment.navigate(navDirections: NavDirections) {
    findNavController().navigate(navDirections)
}

@BindingAdapter(value = ["app:id", "app:url", "app:player", "app:isPlay"], requireAll = true)
fun AppCompatImageView.setPlayer(
    id: Int,
    url: String,
    playerProvider: MediaPlayerProvider,
    isPlay: Boolean
) {
    playerProvider.setSource(url, id)
    when (isPlay) {
        true -> {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_baseline_pause_circle_filled_24
                )
            )
            playerProvider.play()
        }
        false -> {
            setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_baseline_play_circle_24
                )
            )
            playerProvider.pause()
        }
    }
}


@BindingAdapter("setLabel")
fun MaterialTextView.setLabel(param: OptionalSearchParams) {
    with(context) {
        text = when (param) {
            OptionalSearchParams.Genus -> getString(R.string.gennus_label)
            OptionalSearchParams.Subspecies -> getString(R.string.subspecies_label)
            OptionalSearchParams.BackgroundSpecies -> getString(R.string.background_species_label)
            OptionalSearchParams.SoundType -> getString(R.string.sound_type_label)
            OptionalSearchParams.Location -> getString(R.string.location_label)
            OptionalSearchParams.Country -> getString(R.string.country_label)
            OptionalSearchParams.Remarks -> getString(R.string.remarks_label)
            OptionalSearchParams.Recordist -> getString(R.string.recordist_label)
        }
    }
}

@BindingAdapter("setItems")
fun RecyclerView.setItems(arr: ArrayList<OptionalSearchParamModel>) {
    (adapter as OptionalFieldAdapter).setItems(arr)
}

@BindingAdapter("setRecordItems")
fun RecyclerView.setItemss(arr: ArrayList<RecordListItemModel>) {
    (adapter as RecordingsAdapter).submitList(arr.toList())
}

@InverseBindingAdapter(attribute = "setItems")
fun RecyclerView.getItems(): ArrayList<OptionalSearchParamModel> {
    return (adapter as OptionalFieldAdapter).getItems()
}

@BindingAdapter("setItemsAttrChanged")
fun setRecyclerListeners(
    recyclerView: RecyclerView,
    attrChange: InverseBindingListener
) {
    (recyclerView.adapter as OptionalFieldAdapter).listener = object : RecyclerListener {
        override fun changed() {
            attrChange.onChange()
        }
    }
}

@BindingAdapter("onTextChanged")
fun TextInputEditText.onTextChanged(singleLiveEvent: SingleLiveEvent<Boolean>) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            singleLiveEvent.postValue(true)
        }

    })
}


@BindingAdapter("setPosition")
fun TabLayout.setPosition(pos: SearchType) {
    getTabAt(pos.position)!!.select()
}

@InverseBindingAdapter(attribute = "setPosition")
fun getPosition(tabs: TabLayout): SearchType {
    return when (tabs.selectedTabPosition) {
        0 -> {
            SearchType.Normal
        }
        else -> {
            SearchType.Advanced
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

@BindingAdapter("setSrc")
fun AppCompatImageView.setSrc(bool: Boolean) {

    setImageDrawable(
        when (bool) {
            true -> ContextCompat.getDrawable(
                context,
                R.drawable.ic_baseline_pause_circle_filled_24
            )
            false -> ContextCompat.getDrawable(context, R.drawable.ic_baseline_play_circle_24)
        }
    )
}

@BindingAdapter("setError")
fun MaterialTextView.setError(err: ResponseError) {
    text = when (err) {
        ResponseError.Timeout -> {
            context.getString(R.string.timeout_msg)
        }
        ResponseError.NoConnection -> {
            context.getString(R.string.no_connection_msg)
        }
        ResponseError.EmptyQuery -> {
            context.getString(R.string.empty_msg)
        }
        ResponseError.BadResponse -> {
            context.getString(R.string.bad_msg)
        }
        ResponseError.None -> {
            ""
        }
        ResponseError.SomethingElse -> {
            context.getString(R.string.unknown_msg)
        }
    }
}

@BindingAdapter("app:setAlsoBlock")
fun MaterialTextView.setAlsoBlock(arr: Array<String>) {
    val result = StringBuilder()
    arr.forEach {
        result.append(it).append("\n")
    }
    text = if (result.trim().isEmpty()) {
        "-"
    } else {
        result
    }
}
