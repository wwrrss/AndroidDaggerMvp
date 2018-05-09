package willermo.me.dependency

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.support.test.runner.AndroidJUnitRunner
import android.os.StrictMode.setThreadPolicy



class MockTestRunner:AndroidJUnitRunner(){
    override fun onCreate(arguments: Bundle?) {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        super.onCreate(arguments)
    }

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, DependencyTestApp::class.java.name, context)
    }
}