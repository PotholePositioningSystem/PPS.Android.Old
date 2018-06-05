package pps.android.core

import android.app.Service
import android.content.Intent
import android.location.GpsStatus
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.IBinder
import android.app.PendingIntent
import android.content.Context
import android.location.LocationManager
import pps.android.ui.activities.MainActivity
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.content.ContextCompat


@Suppress("DEPRECATION")
class LocationService : Service(), LocationListener, GpsStatus.Listener {
    private lateinit var mLocationManager: LocationManager

    override fun onCreate() {
        super.onCreate()

        val notificationIntent = Intent(this, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val contentIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, 0)

        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mLocationManager.addGpsStatusListener(this)
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 0f, this)
    }

    override fun onGpsStatusChanged(event: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocationChanged(location: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(provider: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
