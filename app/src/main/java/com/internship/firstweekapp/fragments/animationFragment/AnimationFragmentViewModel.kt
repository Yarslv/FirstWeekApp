package com.internship.firstweekapp.fragments.animationFragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Path
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.internship.firstweekapp.R

class AnimationFragmentViewModel :ViewModel() {
    var startX = 0f
    var startY = 0f
    var isPaused = false

    private lateinit var views: Array<View>
    private var animators = arrayListOf<ObjectAnimator>()
    private var times = arrayListOf<Long>()
    fun initViews(arr: Array<View>){
        views = arr
    }

    fun moveToCenter(){
        val path = Path().apply {
            lineTo(startX,startY)
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
                lineTo(startX+300* Math.cos(i).toFloat(), startY+300* Math.sin(i).toFloat())
                i += 0.01
            }
            lineTo(startX,startY)
        }
        for(i in 0 until views.size){
            animators.add(ObjectAnimator.ofFloat(views[i], View.X, View.Y, path).apply {
            duration = 3000
            startDelay = (i*250).toLong()
            start()
        })
        }
    }

    fun pauseAnimation(){
        isPaused = true
        for(i in animators.indices){
            times.add(animators[i].currentPlayTime)
            animators[i].cancel()
        }
    }

    fun resumeAnimation(){
        if(isPaused) {
            for (i in 0 until views.size) {
                animators[i].currentPlayTime = times[i]
                animators[i].start()
            }
            isPaused = false
        }
    }

    fun endAnimation(controller: NavController) {
        var o: ObjectAnimator? = null
        for (i in views){
            o = ObjectAnimator.ofFloat(i, "alpha", 1f, 0f).apply {
                startDelay = 5000
                start()
            }
        }
        o?.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                controller.navigate(R.id.action_animationFragment_to_loginFragment)
            }
        })
    }

}