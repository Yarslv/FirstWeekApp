package com.internship.firstweekapp.ui.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.withStyledAttributes
import com.internship.firstweekapp.R

class CustomTextViewWithLabel(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs) {

    private var text = ""

    fun setText(value: String) {
        text = value
        invalidate()
    }

    private var label = ""

    fun setLabel(value: String) {
        label = value
        invalidate()
    }

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomTextView2) {
            text = getString(R.styleable.CustomTextView2_customText).toString()
            label = getString(R.styleable.CustomTextView2_customLabel).toString()
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
        gravity = Gravity.CENTER_VERTICAL
        textSize = 36f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawText(label, width / 3f, 40f, b)
        canvas.drawRect(0f, 0f, 0f, 100f, a)
        canvas.drawText(text, width / 2f, height / 2f, b)
        super.onDraw(canvas)
    }
}