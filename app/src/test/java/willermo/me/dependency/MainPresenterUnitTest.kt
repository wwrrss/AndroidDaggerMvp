package willermo.me.dependency

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.internal.stubbing.answers.ThrowsException
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import willermo.me.dependency.di.Constants
import willermo.me.dependency.models.Post
import willermo.me.dependency.networking.Api
import willermo.me.dependency.presenters.MainPresenter
import willermo.me.dependency.presenters.MainPresenterImp
import willermo.me.dependency.presenters.MainViewContract
import java.util.concurrent.CountDownLatch

@RunWith(MockitoJUnitRunner::class)
class MainPresenterUnitTest {

    var mainPresenter:MainPresenter? = null
    val mainViewContract = Mockito.mock(MainViewContract::class.java)
    var latch = CountDownLatch(1)
    lateinit var sharedPreferences:SharedPreferences

    @Before
    fun setup(){
        //
        sharedPreferences = Mockito.mock(SharedPreferences::class.java)
        var mockEditor = Mockito.mock(SharedPreferences.Editor::class.java)

        Mockito.`when`(sharedPreferences.getString(Constants.MYVALUE_KEY,Constants.MYVALUE_DEFAULT)).thenReturn(Constants.MYVALUE_DEFAULT)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(mockEditor)
        Mockito.`when`(mockEditor.putString(Constants.MYVALUE_KEY,Constants.MYVALUE_DEFAULT)).thenReturn(mockEditor)
        Mockito.`when`(mockEditor.commit()).thenReturn(true)
        var retrofit = Retrofit.Builder().baseUrl("http://localhost").build()
        var networkBehaviour = NetworkBehavior.create()
        var mockRetrofit = MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehaviour)
                .build()
        var behaviorDelegate = mockRetrofit.create(Api::class.java)
        var mockApi = MockRetrofitInterface(behaviorDelegate)
        mainPresenter = MainPresenterImp(sharedPreferences,mockApi)
        mainPresenter?.setView(mainViewContract)
        Mockito.`when`(mainViewContract.showPostList(Mockito.anyList())).then { latch.countDown() }
    }



    @Test
    fun shouldReturnDefaultText(){
        var value = mainPresenter?.getPersistentValue()
        Assert.assertEquals(value,Constants.MYVALUE_DEFAULT)
    }

    @Test
    fun shouldSaveValueToSharedPreferences(){
       mainPresenter?.savePersistentValue(Constants.MYVALUE_DEFAULT)
        Mockito.verify(sharedPreferences).edit()
    }

    @Test
    fun shooulMakeMockRequestAndCallInterface(){
        mainPresenter?.fetchAllPosts()
        latch.await()
        Mockito.verify(mainViewContract).showPostList(Mockito.anyList())
    }


}