package pps.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pps.android.injection.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by joel on 3/13/18.
 */
class PPSApp : MultiDexApplication(), HasActivityInjector
{
    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

}