package com.internship.firstweekapp.ui.test

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.internship.firstweekapp.BR
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.data.*

class GameFragmentViewModel(private val game: SceneSet) : BaseViewModel() {

    val sli = SingleLiveEvent<String>()

    private var positionInSceneSet = 0
    private var positionInScenes = 0

    val model = Model()

    init {
        model.bkg.set(game.actions[positionInSceneSet].scene_bg)
    }

    fun navigate(view: View) {
        //        sli.postValue(
//            (game.actions[positionInSceneSet].actions[positionInScenes] as MenuText).answMap.get(
//            (view as CustomTextView).text))
    }

    fun click() {
        if (positionInSceneSet >= game.actions.size) return
        positionInScenes++


        if (positionInScenes < game.actions[positionInSceneSet].actions.size) {

            when (game.actions[positionInSceneSet].actions[positionInScenes]) {
                is SimpleText -> {
                    model.textIsVisible.set(true)
                    model.text.set(game.actions[positionInSceneSet].actions[positionInScenes].text)
                }
                is MarkingText -> {
                    model.textIsVisible.set(true)
                    model.label.set((game.actions[positionInSceneSet].actions[positionInScenes] as MarkingText).label)
                    model.text.set((game.actions[positionInSceneSet].actions[positionInScenes] as MarkingText).text)
                }
                is Show -> {
                    model.imgName.set((game.actions[positionInSceneSet].actions[positionInScenes] as Show).text)
                    model.personIsVisible.set(true)
                }
                is MenuText -> {
                    model.menuIsVisible.set(true)
                    val first =
                        (game.actions[positionInSceneSet].actions[positionInScenes] as MenuText).answMap.keys.elementAt(
                            0
                        )
                    model.menu1.set(first)
                    val second =
                        (game.actions[positionInSceneSet].actions[positionInScenes] as MenuText).answMap.keys.elementAt(
                            1
                        )
                    model.menu2.set(second)

                }

            }
        } else {
            model.text.set("")
            model.textIsVisible.set(false)
            if (positionInSceneSet++ < game.actions.size - 1) model.bkg.set(game.actions[positionInSceneSet].scene_bg)
            positionInScenes = 0
        }

    }

    inner class Model : BaseObservable() {
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
        var textIsVisible = ObservableField(false)
            set(value) {
                field = value
                notifyPropertyChanged(BR._all)
            }
    }
}