package pps.android.injection.module

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

/**
 * Created by joel on 3/13/18.
 */
@Module
class AppModule {

    @Provides
    fun provideFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}