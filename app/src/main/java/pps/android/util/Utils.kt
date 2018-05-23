package pps.android.util

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import pps.android.PPSApp

object Utils {
    fun dpToPx(dp: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    fun pxToDp(px: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    val Activity.app: PPSApp
        get() = application as PPSApp

    val Fragment.app: PPSApp
        get() = activity?.application as PPSApp

}
