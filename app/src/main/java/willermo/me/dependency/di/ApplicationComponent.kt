package willermo.me.dependency.di

import dagger.Component
import willermo.me.dependency.activities.MainActivity
import javax.inject.Singleton


/**
 * Created by william on 3/27/18.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
open interface ApplicationComponent{
    fun inject(target: MainActivity)
    

}