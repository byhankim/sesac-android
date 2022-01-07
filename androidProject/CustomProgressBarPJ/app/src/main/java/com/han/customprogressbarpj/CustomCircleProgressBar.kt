package com.han.customprogressbarpj

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator

class CustomCircleProgressBar(context: Context, attrs: AttributeSet): View(context, attrs) {
    // 현재속성 Default setting
    private var progress = 0f

    private var foregroundStrokeWidth = 0f
    private var backgroundStrokeWidth = 0f
    var color = Color.rgb(77, 182, 172) // "#82e9de"

    private var backGroundColor = Color.rgb(0, 134, 125)// "#00867d"

    // 그리기 위한 속성 및 객체들
    private val startAngle = -90
    private var rectF: RectF? = null
    private var backgroundPaint: Paint? = null
    private var foregroundPaint: Paint? = null

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        rectF = RectF()
        val typeArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.CustomCircleProgressBar, 0, 0)
        try {
            // initial progress value
            progress =
                typeArray.getFloat(R.styleable.CustomCircleProgressBar_custom_progress, progress)
            // initial progress stroke width
            foregroundStrokeWidth =
                typeArray.getDimension(
                    R.styleable.CustomCircleProgressBar_custom_progressbar_width,
                    foregroundStrokeWidth
                )
            backgroundStrokeWidth =
                typeArray.getDimension(
                    R.styleable.CustomCircleProgressBar_custom_background_progressbar_width,
                    backgroundStrokeWidth
                )
            // Initial color setup
            color = typeArray.getInt(
                R.styleable.CustomCircleProgressBar_custom_progressbar_color,
                color
            )
            backGroundColor = typeArray.getInt(
                R.styleable.CustomCircleProgressBar_custom_background_progressbar_color, backGroundColor
            )
        } finally {
            typeArray.recycle()
        }
        // background init
        backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG) // 안티얼라이어싱
        backgroundPaint!!.color = backGroundColor
        backgroundPaint!!.style = Paint.Style.STROKE
        backgroundPaint!!.strokeWidth = backgroundStrokeWidth

        // foreground init
        foregroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        foregroundPaint!!.color = color
        foregroundPaint!!.style = Paint.Style.STROKE
        foregroundPaint!!.strokeWidth = foregroundStrokeWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(rectF!!, backgroundPaint!!)
        val angle = 360 * progress / 100
        canvas.drawArc(rectF!!, startAngle.toFloat(), angle, false, foregroundPaint!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = Math.min(width, height)
        setMeasuredDimension(min, min)
        val highStroke =
            if (foregroundStrokeWidth > backgroundStrokeWidth) foregroundStrokeWidth else backgroundStrokeWidth
        rectF!![0 + highStroke / 2, 0 + highStroke / 2, min - highStroke / 2] = min - highStroke / 2
    }

    fun setProgressValue(progress: Float) {
        this.progress = if (progress <= 100) progress else 100f
        invalidate()
    }

    fun setColorValue(color: Int) {
        this.color = color
        foregroundPaint!!.color = color
        invalidate()
        requestLayout()
    }

    override fun setBackgroundColor(backgroundColor: Int) {
        this.backGroundColor = backgroundColor
        foregroundPaint!!.color = backgroundColor
        invalidate()
        requestLayout()
    }

    var progressBarWidth: Float
        get() = foregroundStrokeWidth
        set(strokeWidth) {
            this.foregroundStrokeWidth = strokeWidth
            foregroundPaint!!.strokeWidth = strokeWidth
            requestLayout()
            invalidate()
        }

    var backgroundProgressBarWidth: Float
        get() = foregroundStrokeWidth
        set(value) {
            backgroundStrokeWidth = value
            backgroundPaint!!.strokeWidth = value
            requestLayout()
            invalidate()
        }

    fun setProgressWithAnimation(progress: Float) {
        setProgressWithAnimation(progress, 3000)
    }


    @SuppressLint("ObjectAnimatorBinding")
    private fun setProgressWithAnimation(progress: Float, duration: Int) {
        val objAnimator = ObjectAnimator.ofFloat(this, "progress", progress)
        objAnimator.duration = duration.toLong()
        objAnimator.interpolator = DecelerateInterpolator()
        objAnimator.start()

    }
}