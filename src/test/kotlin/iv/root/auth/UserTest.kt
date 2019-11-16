package iv.root.auth

import iv.root.auth.entity.user.UserController
import iv.root.auth.entity.user.UserDTO
import iv.root.auth.http.ServerResponse
import org.junit.After
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.util.Assert
import java.util.*
import javax.inject.Inject

@SpringBootTest
class UserTest: BaseTest() {

    @Inject
    private lateinit var userController: UserController
    @Inject
    private lateinit var passwordEncoder: PasswordEncoder

    private val ids = LinkedList<Long>()

    @Test
    fun test1() {

        val new = UserDTO(
                id = null,
                firstName = "T1",
                lastName = "T2",
                patronymicName = "T3",
                login = "TTTT",
                password = "TTTT"
        )

        val oldCount = count()
        val response: ResponseEntity<ServerResponse<UserDTO>> = userController.create(new)
        assertHttp(response)
        val body: UserDTO = response.body?.data ?: UserDTO()


        Assert.notNull(body.id)
        ids.add(body.id!!)
        Assert.isTrue(body.firstName.equals(new.firstName))
        Assert.isTrue(body.lastName.equals(new.lastName))
        Assert.isTrue(body.patronymicName.equals(new.patronymicName))
        Assert.isTrue(body.login.equals(new.login))
        Assert.isTrue(passwordEncoder.matches(new.password, body.password))
        Assert.isTrue(count()-1 == oldCount)

        val id: Long = body.id!!
        userController.delete(id)
        Assert.isTrue(count() == oldCount)
    }


    fun count(): Int {
        return userController.getAll().body?.size!!
    }
}