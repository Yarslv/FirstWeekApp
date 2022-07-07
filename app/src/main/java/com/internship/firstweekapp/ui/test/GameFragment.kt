package com.internship.firstweekapp.ui.test

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.switchmaterial.SwitchMaterial
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.data.Game
import com.internship.firstweekapp.databinding.FragmentGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {

    private val navArgs: GameFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vmodel = viewModel

        binding.button.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.START)
        }
        requireActivity().startService(Intent(requireActivity(), MusicService::class.java))

        (binding.navView.menu.findItem(R.id.music_switch).actionView as SwitchMaterial).setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) requireActivity().startService(
                Intent(
                    requireActivity(),
                    MusicService::class.java
                )
            )
            else requireActivity().stopService(Intent(requireActivity(), MusicService::class.java))
        }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.restart -> findNavController().navigate(
                    GameFragmentDirections.actionGameFragmentSelf(
                        0,
                        0
                    )
                )
                R.id.quit -> requireActivity().finish()
            }
            true
        }
    }

    override val viewModel: GameFragmentViewModel by viewModel {
        parametersOf(Game.scenes[navArgs.set])
    }
}