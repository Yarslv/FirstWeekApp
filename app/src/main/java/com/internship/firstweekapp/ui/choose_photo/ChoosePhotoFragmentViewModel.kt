package com.internship.firstweekapp.ui.choose_photo

import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent

class ChoosePhotoFragmentViewModel : BaseViewModel() {

    val navigationEvent = SingleLiveEvent<ImportResource>()

    fun fromGallery() {
        navigationEvent.postValue(ImportResource.Gallery)
    }

    fun fromCamera() {
        navigationEvent.postValue(ImportResource.Camera)
    }
}