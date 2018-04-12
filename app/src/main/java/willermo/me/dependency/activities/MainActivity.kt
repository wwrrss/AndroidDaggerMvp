package willermo.me.dependency.activities

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import willermo.me.dependency.DependencyApp
import willermo.me.dependency.R
import willermo.me.dependency.models.Person
import willermo.me.dependency.presenters.MainPresenter
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.*
import willermo.me.dependency.adapters.PostsAdapter
import willermo.me.dependency.models.Post
import willermo.me.dependency.presenters.MainViewContract

class MainActivity : AppCompatActivity(),MainViewContract {

    @Inject
    lateinit var presenter:MainPresenter

    var postsAdapter:PostsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as? DependencyApp)?.component?.inject(this)
        presenter.setView(this@MainActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsAdapter = PostsAdapter(this@MainActivity)
        mainActivityPostRecycler.adapter = postsAdapter
        var layoutManager = LinearLayoutManager(this)
        mainActivityPostRecycler.layoutManager = layoutManager
        mainAcivityMyValueTextView.text = presenter.getPersistentValue()

    }

    fun saveValue(view:View){
        presenter.savePersistentValue(mainActivityMyValueInput.text.toString())
        mainAcivityMyValueTextView.text = presenter.getPersistentValue()
    }

    @UiThread
    override fun showApiError(errorMessage: String) {
        Toast.makeText(this@MainActivity,errorMessage,Toast.LENGTH_LONG).show()
    }

    @UiThread
    override fun showPostList(postList: List<Post>) {
        postsAdapter?.postList?.clear()
        postsAdapter?.postList?.addAll(postList)
        postsAdapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
