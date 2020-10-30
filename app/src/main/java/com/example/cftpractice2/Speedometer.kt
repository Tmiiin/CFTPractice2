package com.example.cftpractice2

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos


class Speedometer(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var borderColor = Color.parseColor("#5FBDCE")
    private var softGreen = Color.parseColor("#AFF53D")
    private var softRed = Color.parseColor("#FF5240")
    private var colorArrow = Color.MAGENTA
    private var thisBackColor = softGreen
    var maxValue = 125
    var step = Math.PI / maxValue
    var value = 20
    var objectAnimator: ObjectAnimator? = null

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.save()
        canvas?.scale(.5f * width, -1f * height)
        canvas?.translate(1.0f, -1.0f)
        canvas?.drawCircle(0f, 0f, 1f, paint)
        paint.color = softGreen
        canvas?.drawCircle(0f, 0f, 0.8f, paint)
        paint.color = softRed
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 0.005f
        drawDivision(canvas)
        canvas?.save()
        drawArrow(canvas)
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        canvas?.drawCircle(0f, 0f, 0.05f, paint)
        canvas?.restore()
        canvas?.restore()
    }

    fun drawArrow(canvas: Canvas?) {
        canvas?.rotate(90 - 180f * value / maxValue)
        paint.color = colorArrow
        paint.strokeWidth = 0.02f
        canvas?.drawLine(0.01f, 0f, 0f, 1f, paint)
        canvas?.drawLine(-0.01f, 0f, 0f, 1f, paint)
    }

    fun drawDivision(canvas: Canvas?) {
        var scale = 0.9f
        var x2: Double
        var y2: Double
        var x1: Double
        var y1: Double
        for (i in 0..maxValue) {
            x1 = cos(PI - step * i)
            y1 = kotlin.math.sin(PI - step * i)
            if (i % 20 == 0) {
                x2 = x1 * scale * 0.9f
                y2 = y1 * scale * 0.9f
            } else {
                x2 = x1 * scale
                y2 = y1 * scale
            }
            canvas?.drawLine(x1.toFloat(), y1.toFloat(), x2.toFloat(), y2.toFloat(), paint)
        }

    }

    fun setValueAnimated(value2: Int) {
        if (objectAnimator != null) {
            objectAnimator!!.cancel()
        }
        if (value in 101..116) {
            val anim: ValueAnimator
            if (thisBackColor == softGreen) {
                anim = ValueAnimator.ofInt(softGreen, softRed)
                thisBackColor = softRed
            } else anim = ValueAnimator.ofInt(softRed, softGreen)
            anim.addUpdateListener { invalidate() }
            anim.duration = (100 + abs(value - value2) * 5).toLong()
            anim.start()
        }

        objectAnimator = ObjectAnimator.ofInt(this, "value", value, value2)
        objectAnimator?.duration = (100 + abs(value - value2) * 10).toLong()
        objectAnimator?.addUpdateListener { invalidate() }
        objectAnimator?.interpolator = AccelerateDecelerateInterpolator()
        objectAnimator?.start()

    }


/*    private fun drawArrow(canvas: Canvas?) {
        paint.color = maxColor
        val centerPosition = size / 2f
        canvas?.drawLine(centerPosition, (size / 2).toFloat(), 0f, centerPosition, paint)
    }

    private fun move() {
        ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 0F, -200F).apply {
            duration = 10000L
            interpolator = LinearInterpolator()
            start()
        }
    }

    private fun drawSpeed(canvas: Canvas?) {
        paint.color = normalColor
        val rectf = RectF(
            (size) / 6f,
            (size) / 4f,
            (size - size / 6).toFloat(),
            (size - size / 4).toFloat()
        )
        canvas?.drawArc(rectf, 180f, 150f, false, paint)

        val rectf2 = RectF(
            (size) / 6f,
            (size) / 2.5f,
            (size - size / 2.5f).toFloat(),
            (size - size / 2.5).toFloat()
        )
        canvas?.drawArc(rectf2, 180f, 150f, false, paint)
        canvas?.drawPath(speedPath, paint)
    }*/
}