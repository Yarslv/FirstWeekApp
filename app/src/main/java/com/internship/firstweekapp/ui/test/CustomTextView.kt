package com.internship.firstweekapp.ui.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.internship.firstweekapp.R

class CustomTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private var text = ""

    fun setText(value: String) {
        text = value
        invalidate()
    }

    override fun getText() = text

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomTextView2) {
            text = getString(R.styleable.CustomTextView2_customText).toString()
        }
    }


    private var a = Paint().apply {
        background = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(Color.TRANSPARENT, Color.BLACK, Color.BLACK, Color.BLACK, Color.TRANSPARENT)
        )
        setAlpha(0.7f)
    }
    private val b = Paint().apply {
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
        textSize = 36f
    }

    override fun onDraw(canvas: Canvas) {

        canvas.drawRect(0f, 0f, 0f, 100f, a)
        canvas.drawText(text, width / 2f, height / 2f + 10, b)
        super.onDraw(canvas)
    }

    override fun performClick(): Boolean {
        a = Paint().apply {
            background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.TRANSPARENT,
                    Color.parseColor("#cc6404"),
                    Color.parseColor("#cc6404"),
                    Color.parseColor("#cc6404"),
                    Color.TRANSPARENT
                )
            )
            setAlpha(0.95f)
        }
        invalidate()
        return super.performClick()
    }
}