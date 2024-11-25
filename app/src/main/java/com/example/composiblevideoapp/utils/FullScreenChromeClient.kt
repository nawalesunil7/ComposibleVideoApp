package com.example.composiblevideoapp.utils

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity

class FullScreenChromeClient(val activity: ComponentActivity) : WebChromeClient() {
    private var customView: View? = null

    override fun onHideCustomView() {
        (activity.window.decorView as FrameLayout).removeView(this.customView)
        this.customView = null

    }

    @SuppressLint("InternalInsetResource", "DiscouragedApi")
    override fun onShowCustomView(paramView: View, paramCustomViewCallback: CustomViewCallback) {
        if (this.customView != null) {
            onHideCustomView()
            return
        }
        this.customView = paramView
        val param = FrameLayout.LayoutParams(-1, -1)
        try {
            val resId =
                activity.resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resId > 0) {
                param.bottomMargin = activity.resources.getDimensionPixelSize(resId)
            } else {
                param.bottomMargin = 100
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        (activity.window.decorView as FrameLayout).addView(
            this.customView, param
        )
    }
}