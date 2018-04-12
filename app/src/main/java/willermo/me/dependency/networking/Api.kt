package willermo.me.dependency.networking


import retrofit2.Call
import retrofit2.http.GET
import willermo.me.dependency.models.Post

interface Api {
    @GET("posts")
    fun getAllPosts():Call<List<Post>>
}