package com.internship.firstweekapp.ui.tabs.setting

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentSettingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override val viewModel: SettingFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        viewModel.navigateEvent.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SettingFragmentDirections.actionSettingFragment2ToChooseLanguageFragment())
            }
        }

        binding.temperatureChip.setOnLongClickListener {
            val item = ClipData.Item((it as Chip).text)

            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(it.text, mimeTypes, item)

            it.startDragAndDrop(data, View.DragShadowBuilder(it), it, 0)

            true
        }

        val dragListener = View.OnDragListener { dragView, event ->
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {

                    event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    val v = event.localState as View
                    v.visibility = View.INVISIBLE
                    dragView.invalidate()
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION -> {
                    true
                }
                DragEvent.ACTION_DRAG_EXITED -> {
                    dragView.invalidate()
                    true
                }
                DragEvent.ACTION_DROP -> {

                    dragView.invalidate()

                    val v = event.localState as View
                    val owner = v.parent as ViewGroup

                    owner.removeView(v)

                    val dest = dragView as ChipGroup
                    dest.addView(v)

                    v.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    dragView.invalidate()
                    true
                }
                else -> {
                    val v = event.localState as View
                    v.visibility = View.VISIBLE
                    false
                }
            }
        }

        binding.activeFilterChipsGroup.setOnDragListener(dragListener)
        binding.nonactiveFilterChipsGroup.setOnDragListener(dragListener)

    }
}