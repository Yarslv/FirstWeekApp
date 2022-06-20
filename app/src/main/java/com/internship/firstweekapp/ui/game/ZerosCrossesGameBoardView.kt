package com.internship.firstweekapp.ui.game

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Float.min


enum class CellState {
    EMPTY, CROSS, ZERO
}

class ZerosCrossesGameBoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var empties = 9

    private var _stateArr = arrayOf(
        arrayOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        arrayOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY),
        arrayOf(CellState.EMPTY, CellState.EMPTY, CellState.EMPTY)
    )

    var stateArr: Array<Array<CellState>>
        get() = _stateArr
        set(value) {

            _stateArr = value
            invalidate()
        }

    init {
        isClickable = true
    }


    private lateinit var playerFigure: CellState

    var playerFigureIsCross = true
        set(value) {
            field = value
            playerFigure = if (value) {
                CellState.CROSS
            } else {
                CellState.ZERO
            }
        }

    var playerIsFirst = true




    private val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.GREEN
    }

    private val cellPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    private val figurePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 10F
    }

    private val bigRedLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 20F
    }

    private var w = 0f
    private var h = 0f

    override fun onSizeChanged(ww: Int, hh: Int, oldw: Int, oldh: Int) {
        w = min(ww.toFloat(), hh.toFloat())
        h = min(ww.toFloat(), hh.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, w, h, mainPaint)

        for (i in 0..2)
            for (j in 0..2) {
                canvas.drawRect(drawRect(i, j), cellPaint)
                when (stateArr[i][j]) {
                    CellState.EMPTY -> {}
                    CellState.CROSS -> canvas.drawLines(drawCross(i, j), figurePaint)
                    CellState.ZERO -> canvas.drawRoundRect(
                        drawCircle(i, j),
                        100f,
                        100f,
                        figurePaint
                    )
                }
            }

        var win = check(CellState.CROSS)
        if (win.isNotEmpty()) {
            canvas.drawLine(win[0], win[1], win[2], win[3], bigRedLinePaint)
            this.isClickable = false
            return
        }
        win = check(CellState.ZERO)
        if (win.isNotEmpty()) {
            canvas.drawLine(win[0], win[1], win[2], win[3], bigRedLinePaint)
            this.isClickable = false
            return
        }
    }

    private fun drawCircle(x: Int, y: Int): RectF {
        return RectF(
            w / 100 * (x * 32.5f + 7.5f),
            h / 100 * (y * 32.5f + 7.5f), w / 100 * ((x + 1) * 32.5f - 5),
            h / 100 * ((y + 1) * 32.5f - 5)
        )
    }

    private fun drawCross(x: Int, y: Int): FloatArray {
        return floatArrayOf(
            w / 100 * (x * 32.5f + 7.5f), h / 100 * (y * 32.5f + 7.5f),
            w / 100 * (x * 32.5f + 27.5f), h / 100 * (y * 32.5f + 27.5f),
            w / 100 * (x * 32.5f + 27.5f), h / 100 * (y * 32.5f + 7.5f),
            w / 100 * (x * 32.5f + 7.5f), h / 100 * (y * 32.5f + 27.5f)
        )
    }

    private fun drawRect(x: Int, y: Int): RectF {
        return RectF(
            w / 100 * (x * 32.5f + 2.5f),
            h / 100 * (y * 32.5f + 2.5f), w / 100 * ((x + 1) * 32.5f),
            h / 100 * ((y + 1) * 32.5f)
        )
    }

    private fun setCross(x: Int, y: Int) {
        if (isClickable) {
            _stateArr[x][y] = CellState.CROSS
            empties--
            invalidate()
        }
    }

    private fun setZero(x: Int, y: Int) {
        if (isClickable) {
            _stateArr[x][y] = CellState.ZERO
            empties--
            invalidate()
        }
    }

    interface XYSender {
        fun sendXYandType(x: Int, y: Int, type: CellState)
    }

    fun setListener(sender: XYSender) {
        this.sender = sender
    }

    private lateinit var sender: XYSender

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN) {
            val xx = if (event.x / w < 0.33)
                0
            else if (event.x / w < 0.66)
                1
            else
                2

            val yy = if (event.y / h < 0.33)
                0
            else if (event.y / h < 0.66)
                1
            else
                2

            if (playerFigureIsCross) {
//                setCross(xx, yy)
                sender.sendXYandType(xx, yy, CellState.CROSS)
            } else {
                sender.sendXYandType(xx, yy, CellState.ZERO)
            }




            computerTurn()
        }

        return super.onTouchEvent(event)
    }

    private fun computerTurn() {
        if (empties > 0) {
            var x: Int
            var y: Int
            do {
                x = (0..2).random()
                y = (0..2).random()
            } while (stateArr[x][y] != CellState.EMPTY)

            if (playerFigureIsCross)
                sender.sendXYandType(x, y, CellState.CROSS)
            else
                sender.sendXYandType(x, y, CellState.ZERO)
        }
    }

    private fun check(type: CellState): Array<Float> {
        return if (stateArr[0][0] == type && stateArr[0][0] != CellState.EMPTY && stateArr[0][0] == stateArr[0][1] && stateArr[0][0] == stateArr[0][2]) arrayOf(
            w / 100 * (15f + 2.5f),
            h / 100 * (15f + 2.5f),
            w / 100 * (15f + 2.5f),
            h / 100 * (75f + 7.5f)
        )
        else if (stateArr[1][0] == type && stateArr[1][0] != CellState.EMPTY && stateArr[1][0] == stateArr[1][1] && stateArr[1][0] == stateArr[1][2]) arrayOf(
            w / 100 * (45f + 5f),
            h / 100 * (15f + 2.5f),
            w / 100 * (45f + 5f),
            h / 100 * (75f + 7.5f)
        )
        else if (stateArr[2][0] == type && stateArr[2][0] != CellState.EMPTY && stateArr[2][0] == stateArr[2][1] && stateArr[2][0] == stateArr[2][2]) arrayOf(
            w / 100 * (75f + 7.5f),
            h / 100 * (15f + 2.5f),
            w / 100 * (75f + 7.5f),
            h / 100 * (75f + 7.5f)
        )
        else if (stateArr[0][0] == type && stateArr[0][0] != CellState.EMPTY && stateArr[0][0] == stateArr[1][0] && stateArr[0][0] == stateArr[2][0]) arrayOf(
            w / 100 * (15f + 2.5f),
            h / 100 * (15f + 2.5f),
            w / 100 * (75f + 7.5f),
            h / 100 * (15f + 2.5f)
        )
        else if (stateArr[0][1] == type && stateArr[0][1] != CellState.EMPTY && stateArr[0][1] == stateArr[1][1] && stateArr[0][1] == stateArr[2][1]) arrayOf(
            w / 100 * (15f + 2.5f),
            h / 100 * (45f + 5f),
            w / 100 * (75f + 7.5f),
            h / 100 * (45f + 5f)
        )
        else if (stateArr[0][2] == type && stateArr[0][2] != CellState.EMPTY && stateArr[0][2] == stateArr[1][2] && stateArr[0][2] == stateArr[2][2]) arrayOf(
            w / 100 * (15f + 2.5f),
            h / 100 * (75f + 7.5f),
            w / 100 * (75f + 7.5f),
            h / 100 * (75f + 7.5f)
        )
        else if (stateArr[0][0] == type && stateArr[0][0] != CellState.EMPTY && stateArr[0][0] == stateArr[1][1] && stateArr[0][0] == stateArr[2][2]) arrayOf(
            w / 100 * (15f + 2.5f),
            h / 100 * (15f + 2.5f),
            w / 100 * (75f + 7.5f),
            h / 100 * (75f + 2.5f)
        )
        else if (stateArr[0][2] == type && stateArr[0][2] != CellState.EMPTY && stateArr[0][2] == stateArr[1][1] && stateArr[0][2] == stateArr[2][0]) arrayOf(
            w / 100 * (15f + 2.5f),
            h / 100 * (75f + 7.5f),
            w / 100 * (75f + 7.5f),
            h / 100 * (15f + 2.5f)
        )
        else arrayOf()
    }
}

