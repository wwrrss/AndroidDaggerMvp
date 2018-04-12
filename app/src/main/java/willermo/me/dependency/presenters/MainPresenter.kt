package willermo.me.dependency.presenters

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import willermo.me.dependency.di.Constants
import willermo.me.dependency.models.Post
import willermo.me.dependency.networking.Api
import javax.inject.Inject

interface MainPresenter:BasePresenter {



    fun savePersistentValue(theValue:String)
    fun getPersistentValue():String
    fun fetchAllPosts()
    fun setView(viewContract: MainViewContract)

}

class MainPresenterImp @Inject constructor(val sharedPreferences:SharedPreferences, val apiInterface:Api):MainPresenter{



    var viewContract:MainViewContract? = null

    override fun setView(viewContract: MainViewContract) {
        this.viewContract = viewContract
    }

    override fun onResume() {
        fetchAllPosts()
    }

    override fun onDestroy() {

    }

    override fun fetchAllPosts(){
        apiInterface.getAllPosts().enqueue(object : Callback<List<Post>>{

            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                if(response?.isSuccessful==true){
                    viewContract?.showPostList(response.body()!!)
                }else{
                    viewContract?.showApiError("I don't know what happen!, yet")
                }
            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                viewContract?.showApiError(t?.message!!)
            }


        })
    }

    override fun getPersistentValue():String {
        return sharedPreferences.getString(Constants.MYVALUE_KEY,Constants.MYVALUE_DEFAULT)
    }


    override fun savePersistentValue(theValue: String) {
        sharedPreferences.edit().putString(Constants.MYVALUE_KEY,theValue).commit()
    }
}