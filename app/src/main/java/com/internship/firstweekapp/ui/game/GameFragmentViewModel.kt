package com.internship.firstweekapp.ui.game

import android.view.View
import com.google.android.material.textview.MaterialTextView
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.util.*

class GameFragmentViewModel(game: SceneSet) : BaseViewModel() {

    val sli = SingleLiveEvent<String>()

    private var setIterator = game.actions.iterator()
    private var scene = setIterator.next()
    private var sceneIterator = scene.actions.iterator()
    private var sceneAction = sceneIterator.next()

    val model = GameFragmentModel()

    init {
        model.bkg.set(scene.scene_bg)
        model.text.set(sceneAction.text)
    }

    fun navigate(view: View) {
        sli.postValue(
            (sceneAction as MenuText).answMap[(view as MaterialTextView).text]?.text
        )
    }

    fun click() {
        model.bkg.set(scene.scene_bg)
        when (sceneAction) {
            is AuthorText -> {
                model.textIsVisible.set(true)
                model.text.set(sceneAction.text)
            }
            is DialogText -> {
                model.textIsVisible.set(true)
                model.label.set((sceneAction as DialogText).label)
                model.text.set(sceneAction.text)
            }
            is ShowImage -> {
                model.imgName.set(sceneAction.text)
                model.imgEff.set((sceneAction as ShowImage).eff)
                model.personIsVisible.set(true)
            }
            is MenuText -> {
                model.menuIsVisible.set(true)
                val first =
                    (sceneAction as MenuText).answMap.keys.elementAt(
                        0
                    )
                model.menu1.set(first)
                val second =
                    (sceneAction as MenuText).answMap.keys.elementAt(
                        1
                    )
                model.menu2.set(second)

            }
            is JumpToSceneSet -> {
                sli.postValue(sceneAction.text)
            }
        }
        if (sceneIterator.hasNext()) {
            sceneAction = sceneIterator.next()
        } else if (setIterator.hasNext()) {
            scene = setIterator.next()
            sceneIterator = scene.actions.iterator()
            sceneAction = sceneIterator.next()
            model.personIsVisible.set(false)
            model.textIsVisible.set(false)

        }
    }

}
