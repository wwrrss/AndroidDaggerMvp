package willermo.me.dependency.presenters

import willermo.me.dependency.models.Post

interface MainViewContract {

    fun showApiError(errorMessage:String)
    fun showPostList(postList: List<Post>)
}