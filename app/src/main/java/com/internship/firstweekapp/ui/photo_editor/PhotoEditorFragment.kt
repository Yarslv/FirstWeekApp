package com.internship.firstweekapp.ui.photo_editor

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.google.android.material.tabs.TabLayout
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentPhotoEditorBinding
import com.internship.firstweekapp.makeToast
import com.internship.firstweekapp.navigateBack
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoEditorFragment :
    BaseFragment<FragmentPhotoEditorBinding>(R.layout.fragment_photo_editor) {
    override val viewModel: PhotoEditorFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.statesButtonClickEvent.observe(viewLifecycleOwner) {
            when (it) {
                ButtonState.Apply -> {
                    viewModel.photoRepository.add(
                        recreateBitMap(
                            viewModel.photoRepository.getBitmap(),
                            binding.image.colorFilter
                        )
                    )

                    viewModel.updateImage()
                    viewModel.recreateRecyclerArr()
                }
                ButtonState.Save -> {
                    makeToast(getString(R.string.saved, viewModel.photoRepository.save()))

                }
                ButtonState.Crop -> {
                    viewModel.photoRepository.add(binding.cropView.croppedImage!!)
                    viewModel.updateImage()
                    viewModel.recreateRecyclerArr()
                }
            }
        }

        binding.brightnessSlider.addOnChangeListener { slider, value, fromUser ->
            viewModel.btnState.set(ButtonState.Apply)
        }
        binding.contrastSlider.addOnChangeListener { slider, value, fromUser ->
            viewModel.btnState.set(ButtonState.Apply)
        }

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    viewModel.btnState.set(ButtonState.Crop)
                } else
                    viewModel.updateImage()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        }
        )



        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!viewModel.getPreLast()) {
                navigateBack()
            }
        }
    }

    private fun recreateBitMap(bitmap: Bitmap, colorFilter: ColorFilter): Bitmap {
        val new = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(new)
        val paint = Paint()
        paint.colorFilter = colorFilter
        canvas.drawBitmap(new, 0f, 0f, paint)
        return new
    }

}