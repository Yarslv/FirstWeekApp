package com.internship.firstweekapp.ui.record_detail

import androidx.databinding.ObservableBoolean
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class RecordDetailFragmentViewModel : BaseViewModel() {

    val downloadEvent = SingleLiveEvent<Boolean>()
    val playEvent = SingleLiveEvent<Boolean>()

    val isPlay = ObservableBoolean(false)

    fun playPause() {
        isPlay.set(!isPlay.get())
    }

    fun downloadClick() {
        downloadEvent.postValue(true)
    }
}