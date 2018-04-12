package willermo.me.dependency

import android.app.Application
import dagger.android.DaggerApplication
import willermo.me.dependency.di.ApplicationComponent
import willermo.me.dependency.di.ApplicationModule
import willermo.me.dependency.di.DaggerApplicationComponent

/**
 * Created by william on 3/27/18.
 */
class DependencyApp:Application(){

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this))
                    .build()
    }

    open fun getAppComponent():ApplicationComponent{
        return component
    }


}