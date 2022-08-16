package com.internship.firstweekapp.ui.photo_editor

import androidx.databinding.ObservableField
import com.internship.firstweekapp.Constants
import com.internship.firstweekapp.arch.BaseViewModel
import com.internship.firstweekapp.arch.lifecycle.SingleLiveEvent
import com.internship.firstweekapp.photo_saver.PhotoRepository
import com.internship.firstweekapp.ui.photo_editor.shade_recycler.ShadeFilterModel
import com.internship.firstweekapp.ui.photo_editor.shade_recycler.ShadeFilterSelectListener
import com.internship.firstweekapp.ui.photo_editor.shade_recycler.ShadeRecyclerAdapter
import org.koin.java.KoinJavaComponent

class PhotoEditorFragmentViewModel : BaseViewModel(), ShadeFilterSelectListener {

    val photoRepository: PhotoRepository by KoinJavaComponent.inject(PhotoRepository::class.java)

    val action = ObservableField(EditorAction.Brightness)

    val image = ObservableField(photoRepository.out.get())

    val brightnessValue = ObservableField(0f)
    val contrastValue = ObservableField(1f)

    val shade = ObservableField(ShadeFilter.Normal)

    override fun onSelect(shadeFilter: ShadeFilter) {
        shade.set(shadeFilter)
        btnState.set(ButtonState.Apply)
    }

    fun updateImage() {
        brightnessValue.set(0f)
        contrastValue.set(1f)
        shade.set(ShadeFilter.Normal)
        image.set(photoRepository.getBitmap())
        btnState.set(ButtonState.Save)
    }

    fun statesButtonClick() {
        statesButtonClickEvent.postValue(btnState.get())
    }

    val statesButtonClickEvent = SingleLiveEvent<ButtonState>()

    val btnState = ObservableField(ButtonState.Apply)

    fun getPreLast(): Boolean {
        if (photoRepository.fileStack.size > Constants.MAX_SIZE) {
            photoRepository.removeLast()
            updateImage()
            recreateRecyclerArr()
            return true
        }
        return false
    }

    val adapter = ObservableField(createRecyclerAdapter())

    private fun createRecyclerAdapter(): ShadeRecyclerAdapter {
        return ShadeRecyclerAdapter(this).apply {
            val a = arrayListOf<ShadeFilterModel>()
            ShadeFilter.values().forEach {
                a.add(ShadeFilterModel(photoRepository.getBitmap(), it))
            }
            setContent(a)
        }
    }

    fun recreateRecyclerArr() {
        adapter.set(createRecyclerAdapter())
    }
}

