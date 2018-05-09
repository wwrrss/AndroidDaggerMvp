package willermo.me.dependency

import android.util.Log
import okhttp3.OkHttpClient
import willermo.me.dependency.di.ApplicationModule
import willermo.me.dependency.di.DaggerApplicationComponent
import javax.inject.Inject

class DependencyTestApp:DependencyApp(){

    override fun createDaggerComponentGraph() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationTestModule(this))
                .build()


    }


}