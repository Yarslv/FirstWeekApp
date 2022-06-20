package com.internship.firstweekapp.ui.game


import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import com.internship.firstweekapp.arch.BaseViewModel

class GameFragmentViewModel : BaseViewModel() {

    inner class Game : BaseObservable() {
        @Bindable
        var stateArr = ObservableField(
            arrayOf(
                arrayOf(CellState.CROSS, CellState.EMPTY, CellState.EMPTY),
                arrayOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
                arrayOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY)
            )
        )

        fun set(x: Int, y: Int, type: CellState) {
            stateArr.set(stateArr.get().apply {
                this?.get(x)?.set(y, type)
            })
            notifyPropertyChanged(BR.stateArr)
        }
    }

    var game = Game()

    fun setInArr(x: Int, y: Int, type: CellState) {
        game.set(x, y, type)
    }


    var isUserFirst = true
    var isUserCrosses = true
}