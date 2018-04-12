package willermo.me.dependency.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import willermo.me.dependency.BuildConfig
import willermo.me.dependency.networking.Api
import willermo.me.dependency.presenters.MainPresenter
import willermo.me.dependency.presenters.MainPresenterImp
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by william on 3/27/18.
 */
@Module
class ApplicationModule(context: Context){

    val context = context

    @Provides
    @Singleton
    fun providesContext():Context{
        return context
    }

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context):SharedPreferences{
        return context.getSharedPreferences(Constants.PREFERENCE_NAME,Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient():OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        return client
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .baseUrl(Constants.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun providesApiInterface(retrofit: Retrofit):Api{
        val apiInterface = retrofit.create(Api::class.java)
        return apiInterface
    }

    @Provides
    @Singleton
    fun providesMainPresenter(sharedPreferences: SharedPreferences,apiInterface:Api):MainPresenter{
        return MainPresenterImp(sharedPreferences,apiInterface)
    }

}

class Constants {
    companion object {
        const val API_ENDPOINT = "https://jsonplaceholder.typicode.com"
        const val PREFERENCE_NAME = "APP_PREFERENCE"
        const val MYVALUE_KEY = "MYVALUE_KEY"
        const val MYVALUE_DEFAULT = "VALUE_DEFAULT"
    }


}