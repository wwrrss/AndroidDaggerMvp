package willermo.me.dependency

import android.content.Context
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import willermo.me.dependency.di.ApplicationModule
import javax.inject.Singleton


class ApplicationTestModule (context: Context): ApplicationModule(context){

    @Provides
    @Singleton
    override fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()



        return client
    }


}