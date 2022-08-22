package com.internship.firstweekapp.ui.result_list

import androidx.databinding.ObservableField
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.media_player.MediaPlayerProvider
import com.internship.firstweekapp.retrofit.Record

class RecordListItemModel(val record: Record, val playerProvider: MediaPlayerProvider) {

    val playPause = SingleLiveEvent<Boolean>()
    val navigateEvent = SingleLiveEvent<Boolean>()

    val isPlayed = ObservableField(false)

    fun playPauseClick(){

        val new = !isPlayed.get()!!
        playPause.postValue(new)
           }

    fun navigate(){
        navigateEvent.postValue(true)
    }

}