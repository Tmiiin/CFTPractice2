package com.example.cftpractice2

import android.R
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


class Speedometer(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var borderColor = Color.BLACK
    private var maxColor = Color.RED
    private var normalColor = Color.GREEN
    private var borderWidth = 4.0f
    private var size = 500
    private var color = Color.BLACK;

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawSpeedometerBackground(canvas);
        drawSpeed(canvas);
        drawArrow(canvas);
    }


    private fun drawSpeedometerBackground(canvas: Canvas?) {
      val radius = size / 2f
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4F
        canvas?.drawCircle(size / 2f, size / 2f, radius - borderWidth / 2f, paint)
    }

    private fun drawArrow(canvas: Canvas?) {
        paint.color = maxColor
        canvas?.drawPoint(0f, 0f, paint)
    }

    private fun drawSpeed(canvas: Canvas?) {
    //    paint.color = normalColor
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
    }
}