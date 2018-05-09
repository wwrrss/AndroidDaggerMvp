package willermo.me.dependency

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import dagger.android.DaggerApplication
import willermo.me.dependency.di.ApplicationComponent
import willermo.me.dependency.di.ApplicationModule
import willermo.me.dependency.di.DaggerApplicationComponent

/**
 * Created by william on 3/27/18.
 */
open class DependencyApp:Application(){

    open lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        createDaggerComponentGraph()
        if(LeakCanary.isInAnalyzerProcess(this)){
            return
        }
        if(BuildConfig.DEBUG){
            LeakCanary.install(this)
        }
    }

    open fun getAppComponent():ApplicationComponent{
        return component
    }

    open fun createDaggerComponentGraph(){
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


}