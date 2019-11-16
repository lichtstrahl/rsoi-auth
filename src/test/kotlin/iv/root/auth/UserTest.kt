package iv.root.auth

import iv.root.auth.entity.user.UserController
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import javax.inject.Inject

@SpringBootTest
class UserTest {

    @Inject
    private lateinit var userController: UserController

    @Test
    fun test1() {
        val count: Int = userController.getAll().body!!.size
    }
}