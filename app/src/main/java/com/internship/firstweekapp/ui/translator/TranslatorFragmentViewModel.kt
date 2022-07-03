package com.internship.firstweekapp.ui.translator

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.data.repository.PreferenceStorage
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.dict.Dictionary
import com.internship.firstweekapp.dict.State

class TranslatorFragmentViewModel(
    var context: Context,
    var dictionary: Dictionary,
    private var preferenceStorage: PreferenceStorage
) : BaseViewModel() {

    var model = ObservableModel()
    val navEvent = SingleLiveEvent<Boolean>()

    fun changeLanguage() {
        dictionary.change()
        model.langLabel.set(dictionary.getDictLabel())
    }

    fun translate() {
        val answr = dictionary.get(model.text)

        if (model.text.isEmpty()) {
            model.error.set(context.getString(R.string.nothing_input_msg))
            return
        }

        if (answr.isEmpty()) {
            model.error.set(context.getString(R.string.no_found_msg))
            return
        }

        val old = mutableSetOf<RecyclerItemModel>()

        preferenceStorage.getStringSet(Constants.SHARED_PREF_STRING_SET_NAME)?.forEach {
            old.add(RecyclerItemModel(it))
        }


        val new = if (dictionary.state == State.STANDARD)
            mutableSetOf(
                RecyclerItemModel(
                    context.getString(
                        R.string.concate_four,
                        dictionary.from,
                        model.text,
                        answr[0].wordTrans,
                        dictionary.to
                    )
                )
            )
        else
            mutableSetOf(
                RecyclerItemModel(
                    context.getString(
                        R.string.concate_four,
                        dictionary.to,
                        model.text,
                        answr[0].wordOrig,
                        dictionary.from
                    )
                )
            )
        if (old.isNotEmpty()) {
            new += old
            if (new.size > Constants.MAX_LIST_WORD_MEMORY) new.remove(new.last())
        }

        model.elem.set(new)
        val newRaw = mutableSetOf<String>()
        new.forEach {
            newRaw.add(it.from + Constants.SEPARATOR + it.originalWord + Constants.SEPARATOR + it.translatedWord + Constants.SEPARATOR + it.to)
        }
        preferenceStorage.save(Constants.SHARED_PREF_STRING_SET_NAME, newRaw)
        navEvent.postValue(true)
    }

    fun clearError() {
        model.error.set("")
    }

    inner class ObservableModel : BaseObservable() {
        var text = ""

        @Bindable
        var error = ObservableField("")
            set(value) {
                field = value
                notifyPropertyChanged(BR.error)
            }

        @Bindable
        var langLabel = ObservableField(dictionary.getDictLabel())
            set(value) {
                field = value
                notifyPropertyChanged(BR.langLabel)
            }

        @Bindable
        var elem = ObservableField<Set<RecyclerItemModel>>(setOf())
            set(value) {
                field = value
                notifyPropertyChanged(BR.elem)
            }
    }
}

data class RecyclerItemModel(var rawData: String) {
    private var rawDataSplit = rawData.split(Constants.SEPARATOR)
    val from: String
        get() = rawDataSplit[0]
    val originalWord: String
        get() = rawDataSplit[1]
    val translatedWord: String
        get() = rawDataSplit[2]
    val to: String
        get() = rawDataSplit[3]
}