package com.internship.firstweekapp.ui.translator

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.data.repository.PreferenceStorage
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.dict.Dictionary

class TranslatorFragmentViewModel(
    var dictionary: Dictionary,
    var preferenceStorage: PreferenceStorage
) : BaseViewModel() {

    var model = ObservableModel()
    val navEvent = SingleLiveEvent<Boolean>()

    fun changeLanguage() {
        dictionary.change()
        model.langLabel.set(dictionary.getDictLabel())
    }

    fun translate() {
        val answr = dictionary.get(model.text)
        //showing error not work
        if (answr.isEmpty()) return

        val old = preferenceStorage.getStringSet("stringSet")
        //pl ua marking not work
        val new = mutableSetOf("pl===" + model.text + "===" + answr[0] + "===ua")
        if (old!!.isNotEmpty()) {
            new += old as MutableSet
            if (new.size > 16) new.remove(new.last())
        }
        model.elem.set(new.toTypedArray())
        preferenceStorage.save("stringSet", new)
        navEvent.postValue(true)
    }


    inner class ObservableModel : BaseObservable() {
        var text = ""

        @Bindable
        var langLabel = ObservableField(dictionary.getDictLabel())
            set(value) {
                field = value
                notifyPropertyChanged(BR.langLabel)
            }

        @Bindable
        var elem = ObservableField(preferenceStorage.getStringSet("stringSet")!!.toTypedArray())
            set(value) {
                field = value
                notifyPropertyChanged(BR.vmodel)
            }
    }
}