package pps.android

import android.app.Activity
import android.support.multidex.MultiDexApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import pps.android.injection.component.AppComponent
import pps.android.injection.component.DaggerAppComponent
import javax.inject.Inject

/**
 * Created by joel on 3/13/18.
 */
class PPSApp : MultiDexApplication(), HasActivityInjector {
    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        component.inject(this)
    }


}