package willermo.me.dependency.models

import javax.inject.Inject

/**
 * Created by william on 3/27/18.
 */
class Person @Inject constructor() {
    var name = ""
    var age = 0
}

class Post {
    var userId = 0
    var id = 0
    var title = ""
    var body = ""
}