package com.example.composiblevideoapp.utils

import android.content.res.Configuration

object AppUtils {

    fun isTablet(configuration: Configuration): Boolean {
        return configuration.screenWidthDp > 600
    }

}