package com.internship.firstweekapp.fragments.animationFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.internship.firstweekapp.databinding.FragmentAnimationBinding


class AnimationFragment : Fragment() {

    private val model: AnimationFragmentViewModel by viewModels()
    private lateinit var binding: FragmentAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAnimationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


    model.startX =
        resources.displayMetrics.widthPixels.toFloat() / 2 - 20 * resources.displayMetrics.density
    model.startY =
        resources.displayMetrics.heightPixels.toFloat() / 2 - 20 * resources.displayMetrics.density
    model.initViews(
        arrayOf(
            binding.circle1,
            binding.circle2,
            binding.circle3,
            binding.circle4,
            binding.circle5,
            binding.circle6,
            binding.circle7,
            binding.circle8
        )
    )
    model.moveToCenter()
    model.baseAnimation()
    model.endAnimation(findNavController())


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        model.pauseAnimation()
        super.onPause()
    }

    override fun onResume() {
        model.resumeAnimation()
        super.onResume()
    }
}