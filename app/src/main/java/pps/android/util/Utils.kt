package pps.android.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.support.v4.app.Fragment
import android.util.DisplayMetrics
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.mikepenz.iconics.IconicsDrawable
import pps.android.PPSApp
import pps.android.R
import java.util.*


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

    @Synchronized
    fun grantLocationPermissions(activity: Activity, granted: () -> Unit, denied: () -> Unit) {
        Dexter.withActivity(activity)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {

                        if (!report.areAllPermissionsGranted()) {
                            val builder = AlertDialog.Builder(activity)

                            builder.setTitle("Location Access")
                                    .setMessage("PPS needs to access your location to answer questions that include location information.")
                                    .setPositiveButton(android.R.string.ok, { dialog, which ->
                                        denied()
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show()
                        } else {
                            granted()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {

                        val icon = IconicsDrawable(activity, CommunityMaterial.Icon.cmd_crosshairs_gps).colorRes(R.color.colorPrimary).sizeDp(20)

                        val builder = AlertDialog.Builder(activity)

                        builder.setTitle("Location Permissions Required!")
                                .setMessage("PPS currently doesn't have these permission so it can't use your GPS or network to acquire your location.")
                                .setPositiveButton(android.R.string.yes, { dialogInterface, i -> token.continuePermissionRequest() })
                                .setIcon(icon)
                                .show()
                    }
                }).check()
    }
}
