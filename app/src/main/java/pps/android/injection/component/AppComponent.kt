package pps.android.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import pps.android.injection.module.AppModule
import pps.android.PPSApp
import pps.android.ui.fragments.DriverFragment
import pps.android.ui.fragments.PotholesFragment
import javax.inject.Singleton

/**
 * Created by joel on 3/13/18.
 */
@Singleton
@Component(modules = [(AppModule::class),(AndroidInjectionModule::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: PPSApp)
    fun inject(potholeFragment: PotholesFragment)
    fun inject(driverFragment: DriverFragment)

}