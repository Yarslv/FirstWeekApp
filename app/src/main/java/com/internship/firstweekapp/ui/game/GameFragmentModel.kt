package com.internship.firstweekapp.ui.game

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR
import com.internship.firstweekapp.util.Anim

class GameFragmentModel : BaseObservable() {
    @Bindable
    var bkg = ObservableField("")
        set(value) {
            field = value
            notifyPropertyChanged(BR.bkg)
        }

    @Bindable
    var imgName = ObservableField("")
        set(value) {
            field = value
            notifyPropertyChanged(BR.imgName)
        }


    var imgEff = ObservableField(Anim.NONE)

    @Bindable
    var text = ObservableField("")
        set(value) {
            field = value
            notifyPropertyChanged(BR.text)
        }

    @Bindable
    var label = ObservableField("")
        set(value) {
            field = value
            notifyPropertyChanged(BR.label)
        }

    @Bindable
    var menu1 = ObservableField("")
        set(value) {
            field = value
            notifyPropertyChanged(BR.menu1)
        }

    @Bindable
    var menu2 = ObservableField("")
        set(value) {
            field = value
            notifyPropertyChanged(BR.menu2)
        }

    @Bindable
    var menuIsVisible = ObservableField(false)
        set(value) {
            field = value
            notifyPropertyChanged(BR.menuIsVisible)
        }

    @Bindable
    var personIsVisible = ObservableField(false)
        set(value) {
            field = value
            notifyPropertyChanged(BR.personIsVisible)
        }

    @Bindable
    var textIsVisible = ObservableField(true)
        set(value) {
            field = value
            notifyPropertyChanged(BR._all)
        }
}