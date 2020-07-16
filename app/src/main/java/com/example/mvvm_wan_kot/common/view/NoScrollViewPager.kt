package com.example.mvvm_wan_kot.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class NoScrollViewPager : ViewPager {
    private var noScroll = false

    constructor(context: Context): super(context){

    }

    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet){

    }

    init {

    }
    fun setNoScroll(isScroll : Boolean){
        noScroll = isScroll
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (noScroll) super.onTouchEvent(ev) else noScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (noScroll) super.onTouchEvent(ev) else noScroll
    }
}