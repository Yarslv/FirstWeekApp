package com.internship.firstweekapp.fragments.animationFragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Path
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.internship.firstweekapp.R
import kotlin.math.cos
import kotlin.math.sin

class AnimationFragmentViewModel :ViewModel() {
    var startX = 0f
    var startY = 0f
    private var isPaused = false

    private lateinit var views: Array<View>
    private var animators = arrayListOf<ObjectAnimator>()
    private var times = arrayListOf<Long>()
    fun initViews(arr: Array<View>){
        views = arr
    }

    fun moveToCenter(){
        val path = Path().apply {
            moveTo(startX, startY)
        }
        for(element in views)
        ObjectAnimator.ofFloat(element, View.X, View.Y, path).apply {
            start()
        }
    }

    fun baseAnimation(){
        val path = Path().apply {
            moveTo(startX,startY)
            var i = 0.0
            while(i < 2*3.14){
                lineTo(startX + 300 * cos(i).toFloat(), startY + 300 * sin(i).toFloat())
                i += 0.01
            }
            lineTo(startX,startY)
        }
        for (i in views.indices) {
            animators.add(ObjectAnimator.ofFloat(views[i], View.X, View.Y, path).apply {
                duration = 3000
                startDelay = (i * 250).toLong()
                start()
            })
        }
    }

    fun pauseAnimation() {
        isPaused = true
        for (i in animators) {
            times.add(i.currentPlayTime)
            i.cancel()
        }
    }

    fun resumeAnimation(){
        if(isPaused) {
            for (i in views.indices) {
                animators[i].currentPlayTime = times[i]
                animators[i].start()
            }
            isPaused = false
        }
    }

    fun endAnimation(controller: NavController) {
        lateinit var objectAnimator: ObjectAnimator
        for (i in views){
            objectAnimator = ObjectAnimator.ofFloat(i, "alpha", 1f, 0f).apply {
                startDelay = 5000
                start()
            }
        }
        objectAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                controller.navigate(R.id.action_animationFragment_to_loginFragment)
            }
        })
    }
}