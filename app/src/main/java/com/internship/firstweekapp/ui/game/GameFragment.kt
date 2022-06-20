package com.internship.firstweekapp.ui.game

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.internship.firstweekapp.R
import com.internship.firstweekapp.arch.BaseFragment
import com.internship.firstweekapp.databinding.FragmentGameBinding

class GameFragment : BaseFragment<FragmentGameBinding>(R.layout.fragment_game) {
    private val args: GameFragmentArgs by navArgs()

    override val viewModel: GameFragmentViewModel by viewModels()

    override fun setObservers() {
        viewModel.isUserFirst = args.userFirst
        viewModel.isUserCrosses = args.userFigure
        binding.vmodel = viewModel

        binding.game.setListener(object : ZerosCrossesGameBoardView.XYSender {
            override fun sendXYandType(x: Int, y: Int, type: CellState) {
                viewModel.setInArr(x, y, type)
            }
        }
        )

    }

}