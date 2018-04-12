package willermo.me.dependency

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import willermo.me.dependency.di.Constants
import willermo.me.dependency.networking.Api
import willermo.me.dependency.presenters.MainPresenter
import willermo.me.dependency.presenters.MainPresenterImp

@RunWith(MockitoJUnitRunner::class)
class MainPresenterUnitTest {




    var mainPresenter:MainPresenter? = null



    @Before
    fun setup(){
        var sharedPreferences = Mockito.mock(SharedPreferences::class.java)
        var mockEditor = Mockito.mock(SharedPreferences.Editor::class.java)

        Mockito.`when`(sharedPreferences.getString(Constants.MYVALUE_KEY,Constants.MYVALUE_DEFAULT)).thenReturn(Constants.MYVALUE_DEFAULT)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(mockEditor)
        Mockito.`when`(mockEditor.putString(Constants.MYVALUE_KEY,Constants.MYVALUE_DEFAULT)).thenReturn(mockEditor)
        Mockito.`when`(mockEditor.commit()).thenReturn(true)


        var retrofit = Retrofit.Builder().baseUrl("http://abc.com.py").build()
        var api = retrofit.create(Api::class.java)
        mainPresenter = MainPresenterImp(sharedPreferences,api)
    }



    @Test
    fun shouldReturnDefaultText(){
        var value = mainPresenter?.getPersistentValue()
        Assert.assertEquals(value,Constants.MYVALUE_DEFAULT)
    }

    @Test
    fun shouldSaveValueToSharedPreferences(){
       mainPresenter?.savePersistentValue(Constants.MYVALUE_DEFAULT)
    }



}