package com.han.layoutimplpj

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout


class ArticleView: RelativeLayout {

    private lateinit var iconView: ImageView
    private lateinit var aData: ArticleData

    private lateinit var mImageClickListener: OnImageClickListener

    interface OnImageClickListener {
        fun onImageClick(v: View, d: ArticleData)
    }

    fun setOnImageClickListener(listener: OnImageClickListener) {
        mImageClickListener = listener
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(
        context, attrs, defStyleAttr
    ) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet): super (context, attrs) {
        init(attrs)
    }

    fun mapDataToViews(data: ArticleData) {
    }

    private fun init() {
        with (LayoutInflater.from(context).inflate(R.layout.article_summary_view, this)) {
        }
        iconView.setOnClickListener {
            mImageClickListener.onImageClick(this@ArticleView, aData)
        }
    }

    private fun init(attrs: AttributeSet) {
        init()
    }
}