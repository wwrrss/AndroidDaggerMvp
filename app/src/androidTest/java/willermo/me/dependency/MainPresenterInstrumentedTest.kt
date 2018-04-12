package willermo.me.dependency

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import willermo.me.dependency.di.Constants
import willermo.me.dependency.networking.Api
import willermo.me.dependency.presenters.MainPresenter
import willermo.me.dependency.presenters.MainPresenterImp

@RunWith(AndroidJUnit4::class)
class MainPresenterInstrumentedTest {
    var mainPresenter:MainPresenter? = null
    val testValue = "test_value"

    @Before
    fun setup(){
        val appContext = InstrumentationRegistry.getTargetContext()
        val sharedPreferences = appContext.getSharedPreferences(Constants.PREFERENCE_NAME,Context.MODE_PRIVATE)
        var retrofit = Retrofit.Builder().baseUrl(Constants.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var api = retrofit.create(Api::class.java)
        mainPresenter = MainPresenterImp(sharedPreferences,api)
    }

    @Test
    fun shouldReturnValue(){
        val value = mainPresenter?.getPersistentValue()
        Assert.assertNotNull(value)
    }

    @Test
    fun shouldSaveValue(){
        mainPresenter?.savePersistentValue(testValue)
        val savedValue = mainPresenter?.getPersistentValue()
        Assert.assertEquals(savedValue,testValue)
    }

    @Test
    fun shouldMakeApiCall(){
        mainPresenter?.fetchAllPosts()

    }

}