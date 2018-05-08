package willermo.me.dependency

import retrofit2.Call
import retrofit2.mock.BehaviorDelegate
import willermo.me.dependency.models.Post
import willermo.me.dependency.networking.Api

class MockRetrofitInterface(val delegate: BehaviorDelegate<Api>):Api {



    override fun getAllPosts(): Call<List<Post>> {
        val list = arrayListOf<Post>()
        var onePost = Post()
        onePost.id = 1
        onePost.title = "Fake titlel"
        onePost.body = "This is a fake post"
        onePost.userId = 1
        list.add(onePost)

        return delegate.returningResponse(list).getAllPosts()
    }
}